package com.panshi.hujin2.base.security.service.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panshi.hujin2.base.common.factory.MessageFactory;
import com.panshi.hujin2.base.domain.result.BasicResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ZhangZH 2018/4/30 10:20
 */
@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        LOGGER.error(e.getMessage(), e);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        BasicResult<Void> result = BasicResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageFactory.getMsg("G10881402"));
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result.setMessage(MessageFactory.getMsg("G10881400"));
            result.setCode(HttpStatus.OK.value());
        } else if (e instanceof DisabledException || e instanceof LockedException) {
            result.setMessage(MessageFactory.getMsg("G10881401"));
            result.setCode(HttpStatus.FORBIDDEN.value());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
