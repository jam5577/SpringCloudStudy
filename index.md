# Spring Security笔记记录

[toc]



如需启用`Spring Security`，需要==配置类==上加上`@EnableWebSecurity`注解

如需启用注解控制角色的功能，需要在==主启动类==上加上`@EnableGlobalMethodSecurity(securedEnabled = true)`注解

## 架构设计

设计实体时，将单个实体划分为`entity,dto,vo`三个文件夹，分别对数据库中的数据进行创建。

* `entity`对应数据库中的数据结构，使用`Mybatis-Plus`进行代码生成

* `dto`用于在后台进行数据获取、传递和封装

* `vo`用于向前端返回数据，从`dto`和`entity`获取相关数据并返回

例如，对于用户实体，需要创建`User,UserInfo,Role(entity)`，`UserInfoDTO(dto)`，`UserVO(vo)`三个数据类型，`entity`中对应数据库的四张表（有一张关系表），并使用生成的`mapper`文件进行数据库的数据获取，再将获取到的用户信息封装到`UserInfoDTO(dto)`中，用于获取用户的角色信息，前端如果需要用户相关信息，则需要将`dto`转为`vo`再返回。



## Spring Security配置类

在`config`文件夹下新建`SecurityConfig`，并继承`WebSecurityConfigurerAdapter`，在其中重写几个方法：

```java
@Override
//认证  此方法用于设置主要的功能模块，比如路由拦截、权限需求、csrf开启等等
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/login","/","/user/updateUser").permitAll()
            .......
}
```

```java
@Override
//授权  此方法主要用于设置用户所有的角色
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	super.configure(auth);
}
```

```java
@Override
//忽略  此方法主要用于设置需要全局放行的静态资源
public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/static/**","/templates/**","/css/**","/img/**");
}
```

其中还需要写一个`AuthenticationProvider`方法，在其中设置数据加密方式、自定义的`userservice`，并使用`@Bean`注入spring中。

```java
@Bean
public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(new BCryptPasswordEncoder());
    log.debug("调用了userService");
    provider.setUserDetailsService(userService);
    return provider;
}
```

此配置类是将整体认证和授权的逻辑进行统一配置，其中需要将许多方法重写并注入到配置中。



## 方法重写

### 1、`UserDetailService`

此方法主要用于验证登录，在生成的`UserServiceImpl`中实现`UserDetailsService`，并重写其中的方法

```java
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //通过用户名查询用户是否存在
    User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    if(Objects.isNull(user)) throw new UsernameNotFoundException("用户不存在");
    //通过id查询用户的角色信息
    List<String> roleList = userMapper.findRoleById(user.getId());
    log.info("用户角色为{}",roleList);
    //通过用户名查询用户的相关信息
    UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId,user.getId()));
    //将获取到的UserInfo存入内存，方便调用
    servletContext.setAttribute("userInfo",userInfo);
    return UserUtil.conventLoginUser(user,userInfo,roleList,request);
}
```

将`loadUserByUsername`方法改写为使用数据库进行验证，获取到用户信息和角色信息后封装为`UserDTO`并返回。



## 2、`AdminAuthenticationSuccessHandler`

此方法用于实现用户登录成功后的操作，新建类并实现`AuthenticationSuccessHandler`，重写其中的方法。

```java
@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    UserInfoDTO principal = (UserInfoDTO) authentication.getPrincipal();
    log.info(JSON.toJSONString(principal));
    updateUser();
    Result result = new Result<>();
    response.setContentType("application/json;charset=UTF-8");
    String s = new ObjectMapper().writeValueAsString(result.resultData(ResultInfo.LOGIN_SUCCESS));
    response.getWriter().println(s);
    response.sendRedirect("/index");
}

@Async
public void updateUser(){
    UserInfoDTO userInfo = UserUtil.getUserInfoDTO();
    System.out.println(userInfo);
    User user = User.builder()
        .id(UserUtil.getUserInfoDTO().getUserId())
        .ipAddress(UserUtil.getUserInfoDTO().getIpAddress())
        .updateTime(new Date())
        .build();
    userMapper.updateById(user);
}
```



## 3、`AdminAuthenticationFailureHandler`

此方法用于实现用户登录失败后的操作，新建类并实现`AuthenticationFailureHandler`，重写其中的方法。

```java
@Override
public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    log.error("用户登录失败，请重新登录");
    Result result = new Result<>();
    response.setContentType("application/json;charset=UTF-8");
    String s = new ObjectMapper().writeValueAsString(result.resultData(ResultInfo.LOGIN_FAILURE,exception));
    response.getWriter().println(s);
    throw exception;
}
```



## 4、`AdminLogoutSuccessHandler`

此方法用于实现用户成功注销登录后的操作，新建类并实现`LogoutSuccessHandler`，重写其中的方法。

```java
@Override
public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    log.debug("用户成功注销");
    Result result = new Result<>();
    response.setContentType("application/json;charset=UTF-8");
    String s = new ObjectMapper().writeValueAsString(result.resultData(ResultInfo.SUCCESS));
    response.getWriter().print(JSON.toJSONString(s));
    response.sendRedirect("/");
}
```



## 5、`AdminAccessDeniedHandler`

此方法用于实现用户没有权限被禁止访问后的操作，新建类并实现`AccessDeniedHandler`，重写其中的方法。

```java
@Override
public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    Result<Object> result = new Result<>();
    result.resultData(ResultInfo.FORBIDDEN,accessDeniedException);
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().print(result);
    response.sendRedirect("/accessDenied");
}
```



## 6、`AdminSessionStrategy`

此方法用于实现用户的`session`管理，新建类并实现`SessionInformationExpiredStrategy`，重写其中的方法。

```java
@Override
public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
    log.debug("用户被踢出");
    Result<Object> result = new Result<>();
    HttpServletResponse response = event.getResponse();
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().print(result.resultData(405,"用户被踢出"));
}
```








需要完成功能：

* 用户增删改查
* 用户权限分级
* 用户信息展示
* 零件传输流水线



# PureEcho开发日志记录

## 0、快捷键命令行集合 

### 1、`idea`快捷键

| 快捷键             | 执行的操作                                                |
| ------------------ | --------------------------------------------------------- |
| `ctrl+shift+R`     | 全项目的搜索替换操作                                      |
| `ctrl+F`           | 当前文件的全文搜索                                        |
| `双击shift`        | 搜索全目录==文件名==                                      |
| `ctrl+alt+L`       | 格式化当前文件                                            |
| `ctrl+shift+alt+L` | 自定义格式化当前文件                                      |
| `alt+左右方向键`   | 根据文件栏目录顺序切换文件                                |
| `alt+Insert`       | 快捷生成`getter、setter、constructor、Override Methods`等 |
| `ctrl+O`           | 快捷重写方法                                              |
| `ctrl+alt+S`       | 打开设置                                                  |
| `ctrl+alt+shift+S` | 打开项目结构                                              |



### 2、`linux`快捷键

| 快捷键                        | 执行的操作             |
| ----------------------------- | ---------------------- |
| `cd+目标文件夹`               | 进入目标文件夹         |
| `systemctl+start/stop+服务名` | 开启或关闭某服务       |
| `halt`                        | 关机服务               |
| `date`                        | 显示日期               |
| `cal`+`month+year`(可省略)    | 显示目标月份日历       |
| `Tab`                         | 补全命令               |
| `shutdown -h now`             | 立即关机               |
| `ls`                          | 显示目录下的文件名列表 |
| `rm`                          | 删除操作               |



### 3、`docker`快捷键

| 快捷键                 | 执行的操作                                                   |
| ---------------------- | ------------------------------------------------------------ |
| `yum install + 服务名` | 通过`yum`命令安装某服务，后可接`:+版本号`下载某版本的服务    |
| `dockers images`       | 查看当前`docker`中已安装的镜像信息                           |
| `docker ps -a`         | 查看当前`docker`中已经启动的镜像信息                         |
| `docker-compose up`    | 使用`docker`中的`docker-compose.yml`文件进行`docker`服务启动，<br />后续可以使用`-d`表示不打印日志信息，后接文件中某个服务名可以指定启动 |
| `docker logs -f`       | 后接`docker ps`中的容器名可以查看指定服务的日志信息          |
| `docker start/stop`    | 后接`docker ps`中的容器名可以启动/停止服务                   |









## 一、系统开发说明

* 后台管理系统:(springcloud+vue)前后端分离形式进行开发
* 前端App系统: (springcloud+vue)前后端分离形式开发

​	

### 1.1 APP项目

* 需求分析: 存在哪些功能  有哪些模块
  	**视频模块**
   			1.视频列表
   			2.视频详情
   			3.视频评论
   		**分类模块**                后台管理系统(对列表增删改查)
   			1.类别列表
   			2.根据类别查询视频
   		**用户模块**
   			1.发布视频
   			2.搜索视频
   			3.用户登录
   			4.用户视频点赞
   			5.用户收藏视频
   			6.用户不喜欢视频
   			7.我的历史记录
   			8.我的收藏
   			9.用户登录
   			10.编辑用户信息
   			11.已登录
   			12.退出登录
   			13.短信验证码功能


* 概要设计:

  库表设计 

   1.分析系统多少张表  2.表与表之间关系   3.确定表中字段

  ​		视频表
  ​		用户表
  ​		分类表(一张表自连接)
  ​	详细设计: 功能图  流程图  伪代码标注 全部省略

  ​	编码:(环境搭建+业务功能实现)

  * 数据库表结构

  * 1. 后台管理员  amdin
    2. app端用户表 user
    3. 类别表      category
    4. 视频表      video
    5. 评论表      comment 
    6. 收藏表      favorite
    7. 播放记录表  played
    8. 粉丝关注表  following

    


### 1.2编码

#### PureEcho后台系统

* 后台系统开发:  (模块化增删改查)  统计分析
* 后端功能:  1.类别增删改查    2.用户列表(用户查询  模糊查询)     3.视频列表(视频列表 模糊查询)  4.后台管理员登录 退出
* 技术选型:  springboot + springcloud + vue + docker(nacos........) + mysql
* 后台项目结构:
  * pureEcho-admin------------------>维护后端系统全局父项目
  * pureEcho-commons-------------->用来存放后端系统公共代码工具类....
  * pureEcho-users-------------------->用来完成用户列表
  * pureEcho-category---------------->用来完成类别接口
  * pureEcho-videos------------------->用来完成视频接口
  * pureEcho-gateway----------------->后端系统网关服务  路由转发 +  跨域处理     localhost:8888   9999



#### PureEcho后端管理系统页面  vue+elementui 界面

* 环境搭建:
  0. 库表入库
  1. 构建项目结构 
  2. 每个微服务构建一个独立springboot应用
  3. 启动nacos 将每个应用作为微服务注册到nacos
  4. 创建服务网关 配置对应路由规则  + 跨域
  5. 根据接口文档开发接口，连接对应数据  json使用postman调试
  6. 启动后端界面连接网关进行请求交互  去后端打包之后admin-dist/js/app.js 修改网关接口



### 1.3环境搭建

IDE：IntelliJ IDEA，虚拟机软件：VMware workstation 16pro，虚拟机程序：CentOS 7.9，docker版本：20.10.12，redis版本：



#### 1.3.1项目构建

1. 首先在idea中新建一个空的maven项目，将src目录删除作为父项目，并在pom中配置好版本依赖相关代码，导入SpringCloud-alibaba，随后在父项目中依次建立`pureEcho-admin、pureEcho-commons、pureEcho-users、pureEcho-category、pureEcho-videos、pureEcho-gateway`几个子项目，先用将其创建为maven项目，随后在`pureEcho-admin、pureEcho-users、pureEcho-category、pureEcho-videos`中建立SpringBoot相关启动类和配置文件，对于需要引入的spring-boot-starter-web包，将其导入`commons`中，再在其他子项目中引入`commons`依赖，这样可以导入一个spring-boot-starter-web包而多次使用。
2. 在测试每个SpringBoot项目均可以正常启动后，在父项目中新建`docker-compose.yml`文件，并在其中添加如下信息：

> ```yml
> version: "3.2" //docker编译版本
> 
> networks:
> PureEcho_network:
> 
> services:
> nacos: //使用nacos服务
>  image: nacos/nacos-server:2.0.2
>  container_name: nacos
>  ports:  //nacos端口号
>       - "8848:8848"
>     environment:
>       - "JVM_XMS=256m"
>       - "JVM_XMX=256m"
>       - "MODE=standalone"
>     networks:
>       - PureEcho_network
> ```

随后在`idea`中进行`docker`连接配置：

<img src="D:\文件文件文件\研究生文件\研一\TyporaMarkDown\PureEcho.assets\image-20220130013048459.png" alt="image-20220130013048459" style="zoom:100%;" />

使用`Browse Remote Host`进行连接，在弹出界面中输入名称，并选择为`SFTP`，随后在`SSH configuration`后点击展开，输入虚拟机的ip地址（此时的虚拟机需要启动并安装好docker），随后输入设置的用户名和密码，进行测试连接，连接成功后点击确定即可进入文件管理。

3. 在文件目录下新建一个目录`PureEcho`，并将父项目中的`docker-compose.yml`文件拖入其中，随后在`idea`命令行中输入：

   ```shell
   yum install docker-compose  //用于安装docker-compose
   
   systemctl start docker  //启动docker
   
   docker-compose up -d //使用文件进行编译
   ```

   在不报错情况下，需要先下载阿里的`nacos`相关依赖。

4. 在下载好相关依赖后，使用以下命令进行查看：

   ```yml
   docker ps //查看当前容器
   
   docker logs -f 49  //使用查询进行查看新建的nacos，49为容器id前两位
   ```

   ![image-20220130014919884](D:\文件文件文件\研究生文件\研一\TyporaMarkDown\PureEcho.assets\image-20220130014919884.png)

由此，成功开启`nacos`。使用`ip:8848/nacos`即可进入管理页面。用户名密码均为`nacos`。

![image-20220130015120689](D:\文件文件文件\研究生文件\研一\TyporaMarkDown\PureEcho.assets\image-20220130015120689.png)



#### 1.3.2 导入依赖及注册服务

* 在`commons`包下引入`nacos`依赖，

```xml
<!--导入nacos依赖-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

随后在子项目的`application.yml`配置文件中添加配置

```xml
cloud:
    nacos:
      server-addr: 192.168.52.168:8848
```

在`nacos`管理页面可以看到已经启动且注册的服务。



* 在`commons`包下引入数据库相关依赖、`lombok`依赖、`spring-test`相关依赖、`Redis`依赖

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <scope>test</scope>
</dependency>
```

## 注意：在`gateway`微服务中，`spring-boot-starter-web`和`spring-cloud-starter-gateway`依赖不能同时存在，否则会报错

至此，整个`SpringCloud`微服务项目的依赖导入完成，基础脚手架搭建完成，后续还有别的依赖后续再导入



## 二、微服务项目开发

* 在整个项目中，`commons`包中有公共使用的依赖和公共结果返回和公共异常处理类，在其余每一个项目中都引入`commons`包，即可优化依赖导入，统一返回结果和异常处理。

### 2.1`PureEcho-commons`公共项目

* 项目的目录结构为：

```xml

```

#### 2.1.1`Result<T>`统一公共返回结果类



#### 2.1.2`Exception`统一公共异常结果处理类





### 2.2`PureEcho-admin`微服务项目

* 项目的文件结构目录为

```XML
└──AdminApplication.java
├──config
  └──MybatisPlusConfig.java
├──controller
  └──AdminController.java
├──dao
  └──AdminDao.java
├──dto
  └──AdminDTO.java
├──entity
  └──Admin.java
├──handler
├──service
  └──AdminService.java
  ├──impl
    └──AdminServiceImpl.java
├──utils
├──vo
```





### 2.3`PureEcho-category`微服务项目









### 2.4`PureEcho-gateway`微服务项目









### 2.5`PureEcho-users`微服务项目





### 2.6`PureEcho-videos`微服务项目







