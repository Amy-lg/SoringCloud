package com.power.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

/**
 * OpenFeign配置类：配置重试机制、配置日志信息级别
 * 作用：在请求超时后，默认重新请求。当达到重试上限请求还未通过，直接抛出异常
 */
public class FeignConfig {

    /**
     * 配置重试机制
     * @return
     */
    @Bean
    public Retryer retryer() {
        // 默认是不走重试策略
        return Retryer.NEVER_RETRY;
        // 最大请求次数为3，初始间隔时间为100ms，重试最大间隔为1s
        //return new Retryer.Default(100, 1, 3);
    }

    /**
     * 配置feign日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
