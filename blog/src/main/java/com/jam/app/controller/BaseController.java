package com.jam.app.controller;

import com.alibaba.fastjson.JSON;
import com.jam.app.dto.UserDTO;
import com.jam.app.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * @program: SpringCloudStudy
 * @description: 基础controller
 * @author: Mr.Pu
 * @create: 2022-03-08 23:49
 **/

@RequestMapping
@Controller
@Slf4j
public class BaseController {

    public static void inject(Model model) {
        UserDTO userDTO = UserUtil.getUserInfoDTO();
        log.info("user:{}", JSON.toJSONString(userDTO));
        model.addAttribute("image", userDTO.getAvatar());
        model.addAttribute("nickname", Objects.isNull(userDTO.getNickname()) ? "please reset your nickname" : userDTO.getNickname());
        model.addAttribute("ipAddress", userDTO.getIpAddress());
        model.addAttribute("signature", userDTO.getSignature());
        model.addAttribute("email", userDTO.getEmail());
        model.addAttribute("lastLoginTime", userDTO.getLastLoginTime());
    }

    @RequestMapping({"/", "/login"})
    public String toLogin() {
        return "pages-login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        inject(model);
        return "index";
    }

    @RequestMapping("/tables-data")
    public String tablesData() {
        return "tables-data";
    }

    @RequestMapping("/apexCharts")
    public String apexCharts() {
        return "charts-apexcharts";
    }

    @RequestMapping("/eCharts")
    public String eCharts() {
        return "charts-echarts";
    }

    @RequestMapping("/chartsJs")
    public String chartsJs() {
        return "charts-chartjs";
    }

    @RequestMapping("/accordion")
    public String accordion() {
        return "components-accordion";
    }

    @RequestMapping("/alerts")
    public String alerts() {
        return "components-alerts";
    }

    @RequestMapping("/badges")
    public String badges() {
        return "components-badges";
    }

    @RequestMapping("/breadcrumbs")
    public String breadcrumbs() {
        return "components-breadcrumbs";
    }

    @RequestMapping("/buttons")
    public String buttons() {
        return "components-buttons";
    }

    @RequestMapping("/cards")
    public String cards() {
        return "components-cards";
    }

    @RequestMapping("/carousel")
    public String carousel() {
        return "components-carousel";
    }

    @RequestMapping("/list-group")
    public String listGroup() {
        return "components-list-group";
    }

    @RequestMapping("/modal")
    public String modal() {
        return "components-modal";
    }

    @RequestMapping("/pagination")
    public String pagination() {
        return "components-pagination";
    }

    @RequestMapping("/progress")
    public String progress() {
        return "components-progress";
    }

    @RequestMapping("/spinners")
    public String spinners() {
        return "components-spinners";
    }

    @RequestMapping("/tabs")
    public String tabs() {
        return "components-tabs";
    }

    @RequestMapping("/tooltips")
    public String tooltips() {
        return "components-tooltips";
    }

    @RequestMapping("/editors")
    public String editors() {
        return "forms-editors";
    }

    @RequestMapping("/elements")
    public String elements() {
        return "forms-elements";
    }

    @RequestMapping("/layouts")
    public String layouts() {
        return "forms-layouts";
    }

    @RequestMapping("/validation")
    public String validation() {
        return "forms-validation";
    }

    @RequestMapping("/bootstrap")
    public String bootstrap() {
        return "icons-bootstrap";
    }

    @RequestMapping("/boxicons")
    public String boxicons() {
        return "icons-boxicons";
    }

    @RequestMapping("/remix")
    public String remix() {
        return "icons-remix";
    }

    @RequestMapping("/blank")
    public String blank() {
        return "pages-blank";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "pages-contact";
    }

    @RequestMapping("/faq")
    public String faq() {
        return "pages-faq";
    }

    @RequestMapping("/pages-error-404")
    public String error404() {
        return "pages-error-404";
    }

    @RequestMapping("/pages-error-403")
    public String error403() {
        return "pages-error-403";
    }


    @RequestMapping("/toRegister")
    public String register() {
        return "pages-register";
    }

    @RequestMapping("/general")
    public String general() {
        return "tables-general";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        inject(model);
        return "users-profile";
    }


}
