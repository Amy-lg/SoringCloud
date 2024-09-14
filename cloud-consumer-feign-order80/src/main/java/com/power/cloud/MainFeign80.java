package com.power.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主启动类
 * @EnableDiscoveryClient：该注解用于向使用Consul为注册中心时注册服务
 * @EnableFeignClients：该注解用于开启feign客户端
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MainFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(MainFeign80.class, args);
    }
}
