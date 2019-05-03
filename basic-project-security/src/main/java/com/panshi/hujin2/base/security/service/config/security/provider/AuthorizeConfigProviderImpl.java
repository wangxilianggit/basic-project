package com.panshi.hujin2.base.security.service.config.security.provider;

import com.panshi.hujin2.base.security.service.config.rbac.RbacService;
import com.panshi.hujin2.base.security.service.config.rbac.RbacServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author ZhangZhiHao 2018/6/25 10:13
 */
@Component
@Order(Integer.MIN_VALUE)
public class AuthorizeConfigProviderImpl implements AuthorizeConfigProvider {
    @Autowired
    private Map<String, RbacService> rbacServiceMap;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        if (rbacServiceMap.size() == 1) {
            config.anyRequest().access("@ rbacServiceImpl.hasPermission(request,authentication)");
        } else {
            Set<String> strings = rbacServiceMap.keySet();
            for (String string : strings) {
                RbacService rbacService = rbacServiceMap.get(string);
                String simpleName = rbacService.getClass().getSimpleName();
                if (!simpleName.equals(RbacServiceImpl.class.getSimpleName())) {
                    simpleName = (new StringBuilder()).append(Character.toLowerCase(simpleName.charAt(0))).append(simpleName.substring(1)).toString();
                    String expression = "@ " + simpleName + ".hasPermission(request,authentication)";
                    config.anyRequest().access(expression);
                }
            }
        }
    }


}
