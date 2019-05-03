package com.panshi.hujin2.base.job;

import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZhiHao 2018/7/30
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl.job")
public class JobConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * xxl-job 服务地址
     */
    private String adminAddress;

    /**
     * 应用名
     */
    private String executorAppName;

    /**
     * 应用IP，默认为空
     */
    private String executorIp;

    /**
     * 应用需要暴露的端口号，自行定义
     */
    private Integer executorPort;

    /**
     * 应用要打印的定时任务日志文件地址，例如/data/xxlJob/log
     */
    private String executorLogPath;

    /**
     * 定时任务token默认无
     */
    private String accessToken;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        LOGGER.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setIp(executorIp);
        xxlJobExecutor.setPort(executorPort);
        xxlJobExecutor.setAppName(executorAppName);
        xxlJobExecutor.setAdminAddresses(adminAddress);
        xxlJobExecutor.setLogPath(executorLogPath);
        xxlJobExecutor.setAccessToken(accessToken);
        return xxlJobExecutor;
    }
}
