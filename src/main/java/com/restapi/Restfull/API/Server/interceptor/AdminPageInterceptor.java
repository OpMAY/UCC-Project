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
        String URL = request.getRequestURL().toString();
        if (!URL.contains(".map") && !URL.contains("assets") && !URL.contains("js")) {
            log.info("Request URL : " + URL);
        }
        if (URL.startsWith("http://") && !URL.contains("localhost:8080")) {
            response.sendRedirect(URL.replaceAll("http://", "https://"));
            return false;
        } else if (URL.equals("https://weart-page.com") || URL.equals("https://weart-page.com/") || URL.equals("https://www.weart-page.com/") || URL.equals("https://www.weart-page.com") || URL.equals("http://localhost:8080/") || URL.equals("http://localhost:8080")) {
            response.sendRedirect("/admin/main.do");
            return false;
        } else {
            return true;
        }
    }
}
