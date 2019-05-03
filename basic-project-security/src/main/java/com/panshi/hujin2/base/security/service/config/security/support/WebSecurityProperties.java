package com.panshi.hujin2.base.security.service.config.security.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/7/4 9:55
 */
@Component
@ConfigurationProperties(prefix = "security.config")
@Data
public class WebSecurityProperties {
    private String loginProcessingUrl = "/employee/login";
    private List<String> ignores;

}
