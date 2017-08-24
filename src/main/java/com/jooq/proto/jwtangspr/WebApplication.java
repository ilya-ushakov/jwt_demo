package com.jooq.proto.jwtangspr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan
@Configuration
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	JWTTokenGenerator.INSTANCE.generateRandomSecretKey();
        return application.sources(WebApplication.class);
    }
    
    public static void main(final String[] args) throws Exception {
        JWTTokenGenerator.INSTANCE.generateRandomSecretKey();
        SpringApplication.run(WebApplication.class, args);
    }

}
