package com.panshi.hujin2.base.security.web.controller.security;

import com.panshi.hujin2.base.common.factory.MessageFactory;
import com.panshi.hujin2.base.domain.result.BasicResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhangZhiHao 2018/6/21 14:40
 * 对未登录或者登录失效的做统一返回处理
 */
@ApiIgnore
@RestController
public class SecurityController {

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BasicResult<Void> authentication() {
        return BasicResult.error(HttpServletResponse.SC_UNAUTHORIZED, MessageFactory.getMsg("G10881403"));
    }
}