package com.panshi.hujin2.base.security.service.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.service.system.employee.EmployeeBO;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ZhangZH 2018/4/30 10:12
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeBO currentEmployee = EmployeeUtil.getCurrentEmployee();
        BasicResult<EmployeeBO> result = BasicResult.ok(currentEmployee);
        String s = objectMapper.writeValueAsString(result);
        out.write(s);
        out.flush();
        out.close();
    }
}
