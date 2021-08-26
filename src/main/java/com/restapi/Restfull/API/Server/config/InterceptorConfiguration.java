package com.restapi.Restfull.API.Server.config;

import com.restapi.Restfull.API.Server.interceptor.AdminLoginInterceptor;
import com.restapi.Restfull.API.Server.interceptor.AdminPageInterceptor;
import com.restapi.Restfull.API.Server.interceptor.AuthInterceptor;
import com.restapi.Restfull.API.Server.interceptor.DirectoryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    DirectoryInterceptor directoryInterceptor;

    @Autowired
    AdminLoginInterceptor adminLoginInterceptor;

    @Autowired
    AdminPageInterceptor adminPageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> auth_excludeList = new ArrayList<String>();
        /**Auth Directory excluded /auth/* */
        auth_excludeList.add("/*.do");
        auth_excludeList.add("/*.css");
        auth_excludeList.add("/*.admin");
        auth_excludeList.add("/*.ajax");
        auth_excludeList.add("/*.ico");
        auth_excludeList.add("/api/auth/*");
        auth_excludeList.add("/api/auth");
        auth_excludeList.add("/api/login");

        List<String> directory_excludeList = new ArrayList<>();
        List<String> admin_excludeList = new ArrayList<>();


        admin_excludeList.add("/admin/login.do");
        admin_excludeList.add("/admin/login_post.do");
        /**Auth Directory excluded /auth/* */
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/**").excludePathPatterns(auth_excludeList);
        registry.addInterceptor(directoryInterceptor).addPathPatterns("/**").excludePathPatterns(directory_excludeList);
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns(admin_excludeList);
        registry.addInterceptor(adminPageInterceptor).addPathPatterns("/**");
    }
}
