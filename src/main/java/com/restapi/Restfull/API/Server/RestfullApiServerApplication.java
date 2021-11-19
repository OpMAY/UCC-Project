package com.restapi.Restfull.API.Server;

import com.restapi.Restfull.API.Server.config.SessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.http.HttpSessionListener;

@EnableRetry
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.restapi.Restfull.API.Server")
public class RestfullApiServerApplication extends SpringBootServletInitializer {

    @Bean
    public HttpSessionListener httpSessionListener(){
        return new SessionListener();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestfullApiServerApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(RestfullApiServerApplication.class, args);
    }

}
