package com.restapi.Restfull.API.Server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfiguration implements WebMvcConfigurer {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("euc-kr"); // 파일 인코딩 설정
        multipartResolver.setMaxUploadSize(1200 * 1024 * 1024);
        multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 1024);
        return multipartResolver;
    }
}
