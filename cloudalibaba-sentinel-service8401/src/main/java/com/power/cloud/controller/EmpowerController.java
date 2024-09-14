package com.power.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Sentinel授权规则
 * @author Amy
 * @since 2024年9月6日
 */
@RestController
@Slf4j
public class EmpowerController {

    @GetMapping("/empower")
    public String requestSentinel() {
        log.info("测试Sentinel授权规则,黑名单禁止请求；白名单可以请求");
        return "Sentinel授权规则";
    }
}
