package com.panshi.hujin2.base.security.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决跨域问题
 *
 * @author 刘世茂
 * @date 2017年5月4日
 */
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@WebFilter(filterName = "crossFilter", urlPatterns = "/*")
//@Component
public class CrossFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrossFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("crossFiler init");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
        servletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE,PATCH");
        servletResponse.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,SessionToken,Cookie");
        servletResponse.setHeader("Access-Control-Max-Age", "3600");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Access-Control-Expose-Headers", "*");
        if ("OPTIONS".equals(servletRequest.getMethod())) {
            LOGGER.info("---------用户请求 options" + ((HttpServletRequest) request).getRequestURI());
            servletResponse.setStatus(HttpStatus.ACCEPTED.value());
            return;
        }
        LOGGER.info("---------用户请求" + ((HttpServletRequest) request).getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
