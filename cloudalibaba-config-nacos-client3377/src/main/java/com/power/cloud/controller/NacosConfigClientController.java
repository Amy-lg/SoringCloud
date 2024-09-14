package com.power.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos配置中心控制层
 * @RefreshScope：
 * 此注解可以获取Nacos配置中心配置内容，并在配置中心文件有所修改时动态刷新后端程序获取到的值
 * @author Amy
 * @since 2024年8月15日
 */
@RestController
@RefreshScope
public class NacosConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return "configInfo:" + configInfo;
    }
}