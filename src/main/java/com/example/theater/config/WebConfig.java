package com.example.theater.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.theater.filter.TokenFilter;

@Configuration
public class WebConfig {

    private final TokenFilter tokenFilter;

    public WebConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public FilterRegistrationBean<TokenFilter> loggingFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(tokenFilter);
        registrationBean.addUrlPatterns("/schedule/*");
        return registrationBean;
    }
}
