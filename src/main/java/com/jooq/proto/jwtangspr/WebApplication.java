package com.jooq.proto.jwtangspr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan
@Configuration
public class WebApplication {
//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new JwtFilter());
//        registrationBean.addUrlPatterns("/api/*");
//
//        return registrationBean;
//    }

    public static void main(final String[] args) throws Exception {
        JWTTokenGenerator.INSTANCE.generateRandomSecretKey();
        SpringApplication.run(WebApplication.class, args);
    }

}
