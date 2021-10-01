package com.restapi.Restfull.API.Server.interceptor;

import com.restapi.Restfull.API.Server.models.Admin;
import com.restapi.Restfull.API.Server.services.AdminService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object admin = session.getAttribute("adminLogin");
            if (admin != null) {
                Admin admin1 = (Admin) admin;
                if (adminService.loginAdmin(admin1.getId(), admin1.getPw()) != null) {
                    return true;
                }
            }
        }
        response.sendRedirect("/admin/login.do");
        return false;
    }
}
