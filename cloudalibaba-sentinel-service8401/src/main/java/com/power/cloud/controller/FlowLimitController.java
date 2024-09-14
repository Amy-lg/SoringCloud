package com.power.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流量限制监控控制类
 *
 * @author Amy
 * @since 2024年8月22日
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----testA----";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----testB----";
    }
}
