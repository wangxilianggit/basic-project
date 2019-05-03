package com.panshi.hujin2.base.security.service.config.security;

import com.panshi.hujin2.base.security.service.config.security.provider.AuthorizeConfigProvider;
import com.panshi.hujin2.base.security.service.config.security.support.WebSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/21 14:26
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AuthorizeConfigProvider authorizeConfigProvider;
    @Autowired
    private WebSecurityProperties webSecurityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> ignores = webSecurityProperties.getIgnores();
        if (ignores == null) {
            ignores = new ArrayList<>();
        }
        ignores.add("/v2/api-docs");
        ignores.add("/swagger-resources");
        ignores.add("/swagger-ui.html");
        web.ignoring().antMatchers(ignores.toArray(new String[0]));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl(webSecurityProperties.getLoginProcessingUrl())
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and().authorizeRequests()
                .antMatchers(webSecurityProperties.getLoginProcessingUrl(), "/authentication/require").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
        authorizeConfigProvider.config(http.authorizeRequests());
    }
}