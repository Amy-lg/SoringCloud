package com.power.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 3377主启动作为Nacos配置服务微服务模块的主启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main3377 {
    public static void main(String[] args) {
        SpringApplication.run(Main3377.class, args);
    }
}
