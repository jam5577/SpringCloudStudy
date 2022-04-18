package com.java.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-04-16 10:03
 **/

@Component
public class PushMessage {

    private static final String APP_ID = "wx159a303f3c74803f";

    private static final String APP_SECRET = "52a0a73d03c73da7182ce27b64d8764f";

    //    private static final String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    private static final String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=";

    private static final String AT_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}";

    private static String ACCESS_TOKEN;

    private static final String WX_ID = "oLzHb5DwPxmGl6oauUbImwYhWWuA";

    private static final String TEMPLATE_ID = "VEInskXORjev84CWr_t4BDC1AG-mhPFKf2633qpnzKc";

    public static void main(String[] args) {
        push();
    }

    public static void push() {
        Map<String, String> map = new HashMap<>();
        map.put("APPID", APP_ID);
        map.put("APPSECRET", APP_SECRET);
        //获取AccessToken
        RestTemplate restTemplate = new RestTemplate();
        JSONObject access = restTemplate.getForObject(AT_URL, JSONObject.class, map);
        if (!Objects.isNull(access)) {
            System.out.println("access:" + JSON.toJSONString(access));
            if (access.containsKey("access_token")) {
                ACCESS_TOKEN = access.getString("access_token");
                System.out.println("获取成功,token:" + ACCESS_TOKEN);
            }
        }
        Map<String, String> miniprogram = new HashMap<>();
        miniprogram.put("appid", APP_ID);
        miniprogram.put("pagepath", "/");
        Map<String, Map<String, String>> data = new HashMap<>();
        Map<String, String> keyword1 = new HashMap<>();
        keyword1.put("value", "测试标题1");
        keyword1.put("color", "#173177");
        Map<String, String> keyword2 = new HashMap<>();
        keyword2.put("value", "测试标题2");
        keyword2.put("color", "#173177");
        data.put("thing1", keyword1);
        data.put("thing2", keyword2);
        Map<String, Object> mp_template_msg = new HashMap<>();
        mp_template_msg.put("appid", APP_ID);
        mp_template_msg.put("template_id", TEMPLATE_ID);
        mp_template_msg.put("url", "http://weixin.qq.com/download");
        mp_template_msg.put("miniprogram", miniprogram);
        mp_template_msg.put("data", data);

        //发送消息
        JSONObject post = restTemplate.postForObject(PUSH_URL + ACCESS_TOKEN, MiniprogramTemplate.builder()
                .touser(WX_ID)
                .mp_template_msg(mp_template_msg)
//                .url("http://weixin.qq.com/download")
//                .miniprogram(miniprogram)
//                .data(data)
                .build(), JSONObject.class);
        System.out.println(post);
    }

}
