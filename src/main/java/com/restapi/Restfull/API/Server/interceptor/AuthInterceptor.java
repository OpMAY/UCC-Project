package com.restapi.Restfull.API.Server.interceptor;

import com.restapi.Restfull.API.Server.exceptions.AuthException;
import com.restapi.Restfull.API.Server.services.SecurityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * return false시 controller로 요청 안감
 */
@Log4j2
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION)
                    .substring(request.getHeader(HttpHeaders.AUTHORIZATION)
                            .lastIndexOf("bearer ") + 7);
            if (securityService.validateToken(token)) {
                return true;
            } else {
                throw new AuthException(new Exception());
            }
        } catch (NullPointerException e) {
            throw new AuthException(e);
        } catch (Exception e) {
            throw new AuthException(e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
