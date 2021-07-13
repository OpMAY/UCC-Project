package com.restapi.Restfull.API.Server.controller.admin;

import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import com.restapi.Restfull.API.Server.services.AdminService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Log4j2
@Controller
public class AdminController implements ControllerInitialize {
    private ModelAndView modelAndView;

    @Autowired
    private AdminService adminService;

    @Override
    public void init(String method) {
        log.info(method);
    }

    @RequestMapping(value = "/admin/login.do", method = RequestMethod.GET)
    public ModelAndView Login() {
        modelAndView = new ModelAndView("/pages/auth/login");
        init("GET Login");
        return modelAndView;
    }

    @PostMapping("/admin/login")
    public ModelAndView adminLogin(@RequestParam(value = "id") String id,
                              @RequestParam(value = "password") String password,
                              HttpSession session,
                              Model model) {
        log.info("id: " + id);
        log.info("password: " + password);

        if (session.getAttribute("adminLogin") != null)
            session.removeAttribute("adminLogin");

        modelAndView = new ModelAndView("/pages/auth/login");
        if (adminService.loginAdmin(id, password) != null) {
            session.setAttribute("adminLogin", adminService.loginAdmin(id, password));
            model.addAttribute("msg", "로그인 성공");
            modelAndView.addObject("result", "true");
            init("GET Login");
        }else{
            modelAndView.addObject("result", "false");
        }
        return modelAndView;
    }
}
