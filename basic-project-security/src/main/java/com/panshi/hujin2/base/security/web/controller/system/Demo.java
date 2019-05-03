//package com.panshi.hujin2.base.security.web.controller.system;
//
//import com.panshi.hujin2.base.security.dao.mapper.system.menu.controller.ControllerAuthenticationMapper;
//import com.panshi.hujin2.base.security.dao.model.system.menu.ControllerAuthentication;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.Map;
//
///**
// * @author ZhangZhiHao 2018/6/29 15:32
// * 读取controller URL和method的方法
// */
//@RestController
//@RequestMapping("/demo")
//public class Demo {
//    @Autowired
//    private ControllerAuthenticationMapper controllerAuthenticationMapper;
//
//    @GetMapping("/init")
//    public void init(HttpServletRequest request, HttpServletResponse response) {
//        //获取上下文对象
//        WebApplicationContext wac = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        //通过上下文对象获取RequestMappingHandlerMapping实例对象
//        RequestMappingHandlerMapping bean = wac.getBean(RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
//        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
//            ControllerAuthentication controllerAuthentication = new ControllerAuthentication();
//            PatternsRequestCondition prc = rmi.getPatternsCondition();
//            String pattern = new ArrayList<>(prc.getPatterns()).get(0);
//            controllerAuthentication.setControllerUrl(pattern);
//            ArrayList<RequestMethod> requestMethods = new ArrayList<>(rmi.getMethodsCondition().getMethods());
//            if (CollectionUtils.isNotEmpty(requestMethods)) {
//                controllerAuthentication.setRequestMethod(requestMethods.get(0).toString());
//
//            }
//            controllerAuthenticationMapper.add(controllerAuthentication);
//        }
//    }
//}