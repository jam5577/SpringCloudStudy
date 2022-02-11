package com.jam.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jam.api.UserInfoApi;
import com.jam.entity.MailSender;
import com.jam.entity.UserInfo;
import com.jam.mapper.UserInfoMapper;
import com.jam.utils.EmailUtils;
import com.jam.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    private final EmailUtils emailUtils;
    private final UserInfoMapper userInfoMapper;
    @Autowired
    public UserInfoController(EmailUtils emailUtils, UserInfoMapper userInfoMapper){
        this.emailUtils = emailUtils;
        this.userInfoMapper =userInfoMapper;
    }

//    private static final String path="D:\\WebProject\\SpringCloudStudy\\excel-operation\\src\\test\\java\\com\\jam\\excel\\poi\\";
//    private static final String filename = path + UUID.randomUUID() + ".xlsx";


    private List<UserInfoVO> data(){
        List<UserInfoVO> list = new ArrayList<>();
        UserInfoVO userInfoVO = new UserInfoVO();
        List<UserInfo> userInfos = userInfoMapper.selectList(new LambdaQueryWrapper<>());
        for (UserInfo userInfo : userInfos) {
            BeanUtils.copyProperties(userInfo,userInfoVO);
            list.add(userInfoVO);
        }
        return list;
    }

    @RequestMapping(UserInfoApi.DOWNLOAD_API)
    public void Write(HttpServletResponse response) throws IOException {
        setExcelRespProp(response,UUID.randomUUID().toString());
        EasyExcel.write(response.getOutputStream(),UserInfoVO.class).sheet(this.getClass().getName()).doWrite(data());
    }

    @RequestMapping(UserInfoApi.SIMPLE_EMAIL)
    public String simpleMailTest(){
        emailUtils.sendSimpleMail("2983015231@qq.com","test","this is a test");
        return "ok";
    }

    @RequestMapping(UserInfoApi.HTML_EMAIL)
    public String htmlMailTest(){
        emailUtils.sendHtmlMail("zhiweipu2021@tongji.edu.cn","test","<h1>this is a test</h1>");
        return "ok";
    }

    @RequestMapping(UserInfoApi.ATTACH_EMAIL)
    public String attachMailTest(){
        emailUtils.sendAttachmentsMail("zhiweipu2021@tongji.edu.cn","test",
                "<h1>this is a test</h1>",
                "D:\\WebProject\\SpringCloudStudy\\excel-operation\\src\\main\\resources\\application.yml");
        return "ok";
    }

    private void setExcelRespProp(HttpServletResponse response,String rawFileName) throws UnsupportedEncodingException{
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition","attachment;filename*=utf-8''"+fileName+".xlsx");
    }
}

