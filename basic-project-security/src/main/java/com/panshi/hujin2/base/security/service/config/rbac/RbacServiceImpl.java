package com.panshi.hujin2.base.security.service.config.rbac;

import com.panshi.hujin2.base.security.dao.model.system.menu.ControllerAuthentication;
import com.panshi.hujin2.base.security.service.system.employee.EmployeeBO;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author ZhangZhiHao 2018/6/25 10:14
 */
@Component
public class RbacServiceImpl implements RbacService {
    protected AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (customIgnoreRule(request)) {
            return true;
        }
        Boolean hasPermission = false;
        //是登录的用户
        if (principal instanceof UserDetails) {
            // todo @author ZhangZhiHao 权限需配置
            hasPermission = true;
            //EmployeeBO currentEmployee = EmployeeUtil.getCurrentEmployee();
            //List<RoleOutputBO> roleOutputBOS = currentEmployee.getRoleOutputBOS();
            //List<String> names = roleOutputBOS.stream().map(RoleOutputBO::getName).collect(toList());
            //if (CollectionUtils.isNotEmpty(names)) {
            //    if (names.contains("admin")) {
            //        return true;
            //    }
            //}
            //List<ControllerAuthentication> controllerAuthentications = currentEmployee.getControllerAuthentications();
            //for (ControllerAuthentication controllerAuthentication : controllerAuthentications) {
            //    if (antPathMatcher.match(controllerAuthentication.getControllerUrl(), request.getRequestURI())) {
            //        if (controllerAuthentication.getRequestMethod().equals(request.getMethod())) {
            //            hasPermission = true;
            //            break;
            //        }
            //    }
            //}
        }
        return hasPermission;
    }

}
