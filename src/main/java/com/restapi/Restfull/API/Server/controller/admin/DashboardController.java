package com.restapi.Restfull.API.Server.controller.admin;

import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
public class DashboardController implements ControllerInitialize {
    private ModelAndView modelAndView;

    @Override
    public void init(String method) {
        log.info(method);
    }

    @RequestMapping(value = "/admin.do", method = RequestMethod.GET)
    public ModelAndView Dashboard() {
        modelAndView = new ModelAndView("index");
        init("Get Dashboard");
        return modelAndView;
    }
}
