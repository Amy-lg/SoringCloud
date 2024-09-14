package com.power.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SpringCloud Gateway主启动类
 * @author Amy
 * @since 2024/8/7
 */
@SpringBootApplication
@EnableDiscoveryClient // 将此模块服务注册进Consul
public class Main9527 {
    public static void main(String[] args) {
        SpringApplication.run(Main9527.class, args);
    }
}
