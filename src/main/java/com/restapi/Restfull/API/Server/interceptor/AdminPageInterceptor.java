package com.restapi.Restfull.API.Server.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class AdminPageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURL().toString().contains(".map")) {
            log.info("Request URL : " + request.getRequestURL().toString());
        }
        if (request.getRequestURL().toString().startsWith("http://")) {
            response.sendRedirect(request.getRequestURL().toString().replaceAll("http://", "https://"));
            return false;
        } else if (request.getRequestURL().toString().equals("https://weart-page.com") || request.getRequestURL().toString().equals("https://weart-page.com/") || request.getRequestURL().toString().equals("https://www.weart-page.com/") || request.getRequestURL().toString().equals("https://www.weart-page.com") || request.getRequestURL().toString().equals("http://localhost:8080/") || request.getRequestURL().toString().equals("http://localhost:8080")) {
            response.sendRedirect("/admin/main.do");
            return false;
        } else {
            return true;
        }
    }
}
