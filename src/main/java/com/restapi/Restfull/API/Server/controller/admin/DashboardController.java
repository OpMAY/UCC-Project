package com.restapi.Restfull.API.Server.controller.admin;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import com.restapi.Restfull.API.Server.services.AdminService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
public class DashboardController implements ControllerInitialize {
    private ModelAndView modelAndView;

    @Autowired
    private AdminService adminService;

    @Override
    public void init(String method) {
        log.info(method);
    }

    @RequestMapping(value = "/admin/main.do", method = RequestMethod.GET)
    public ModelAndView AdminMain() {
        return adminService.getAdminMain();
    }

    @Data
    class SnsDate{
        private String date;
    }

    @RequestMapping(value = "/admin/main_sns.do", method = RequestMethod.POST)
    @ResponseBody
    public String MainSNS(@RequestBody String body){
        SnsDate date = new Gson().fromJson(body, SnsDate.class);
        return adminService.getUserSNSByDate(date.getDate());
    }
}
