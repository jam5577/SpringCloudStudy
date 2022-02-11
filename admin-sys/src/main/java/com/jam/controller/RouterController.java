package com.jam.controller;

import com.alibaba.fastjson.JSON;
import com.jam.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

/**
 * @program: SpringCloudStudy
 * @description: 路由管理
 * @author: Mr.Pu
 * @create: 2022-01-26 23:08
 **/
@Api("路由控制")
@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RouterController {

    private final ServletContext servletContext;

    @ApiModelProperty("跳转到登录页")
    @RequestMapping({"/","/toLoginForm"})
    public String login(){
        return "login";
    }

    @ApiModelProperty("跳转到首页")
    @RequestMapping("/index")
    public String index(Model model){
        Object userInfo = servletContext.getAttribute("userInfo");
        System.out.println(userInfo);
        model.addAttribute("user", JSON.toJSONString(UserUtil.getUserInfoDTO()));
        model.addAttribute("userInfo",JSON.toJSONString(userInfo));
        return "index";
    }

    @ApiModelProperty("跳转到403页")
    @RequestMapping("/accessDenied")
    public String accessDenied(){
        return "403";
    }

//    @ApiModelProperty("跳转到404页")
//    @RequestMapping("*")
//    public String notFound(){
//        return "404";
//    }

    @Secured("ROLE_admin")
    @ApiModelProperty("跳转到按钮页")
    @RequestMapping("/buttons")
    public String button(){
        return "buttons";
    }

    @Secured("ROLE_root")
    @ApiModelProperty("跳转到卡片页")
    @RequestMapping("/cards")
    public String card(){
        return "cards";
    }

    @ApiModelProperty("跳转到图表页")
    @RequestMapping("/charts")
    public String chart(){
        return "charts";
    }

    @ApiModelProperty("跳转到忘记密码页")
    @RequestMapping("/forgot")
    public String forgot(){
        return "forgot-password";
    }

    @ApiModelProperty("跳转到表格页")
    @RequestMapping("/tables")
    public String table(){
        return "tables";
    }

    @ApiModelProperty("跳转到动效页")
    @RequestMapping("/animation")
    public String animation(){
        return "utilities-animation";
    }

    @ApiModelProperty("跳转到边界页")
    @RequestMapping("/borders")
    public String border(){
        return "utilities-border";
    }

    @ApiModelProperty("跳转到颜色页")
    @RequestMapping("/color")
    public String color(){
        return "utilities-color";
    }

    @ApiModelProperty("跳转到其他页")
    @RequestMapping("/other")
    public String other(){
        return "utilities-other";
    }
}
