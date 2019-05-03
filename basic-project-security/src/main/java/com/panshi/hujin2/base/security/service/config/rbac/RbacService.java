package com.panshi.hujin2.base.security.service.config.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangZhiHao 2018/6/25 10:13
 */
public interface RbacService {

    /**
     * 校验员工权限
     *
     * @param request        http请求
     * @param authentication 当前用户认证信息
     * @return true代表有权, false代表无权
     */
    Boolean hasPermission(HttpServletRequest request, Authentication authentication);

    /**
     * 可实现此方法来添加每个项目自定义的忽略规则
     *
     * @param request http请求,可从中取出URL,method等
     * @return 返回true则直接通过, 返回false则会继续判断security模块自带的一些校验
     */
    default Boolean customIgnoreRule(HttpServletRequest request) {
        return false;
    }
}
