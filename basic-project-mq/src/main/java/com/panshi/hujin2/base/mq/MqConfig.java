package com.panshi.hujin2.base.mq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MqConfig {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    @ConfigurationProperties(prefix = "rocketmq.producer")
    @Scope("singleton")
    public MyProducer myProducer(){
        return new MyProducer();
    }

    @Bean(initMethod = "init",destroyMethod = "destroy")
    @ConfigurationProperties(prefix = "rocketmq.consumer")
    @Scope("singleton")
    public MyConsumer myConsumer(){
        return new MyConsumer();
    }


}
