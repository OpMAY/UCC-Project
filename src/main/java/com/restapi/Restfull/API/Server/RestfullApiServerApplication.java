package com.restapi.Restfull.API.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.restapi.Restfull.API.Server")
public class RestfullApiServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestfullApiServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestfullApiServerApplication.class, args);
    }

}
