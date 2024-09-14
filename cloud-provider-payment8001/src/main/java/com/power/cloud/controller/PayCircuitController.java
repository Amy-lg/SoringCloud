package com.power.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 此ctl演示Resilience4j CircuitBreaker 熔断
 * @author Amy
 * @since 2024/08/01
 */
@RestController
public class PayCircuitController {

    /**
     * resilience4j CircuitBreaker测试方法
     * @param id
     * @return
     */
    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("circuit id 不能为负数");
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Hello,circuit! inputId:" + id + "\t" + IdUtil.simpleUUID();
    }

    /**
     * resilience4j bulkhead 信号量舱壁隔离测试
     * @param id
     * @return
     */
    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("--bulkhead id 不能为负数--");
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Hello，bulkhead!inputId:" + id + "\t" + IdUtil.simpleUUID();
    }

    /**
     * resilience4j RateLimit限流测试
     * @param id
     * @return
     */
    @GetMapping("/pay/rateLimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return "Hello,限流功能测试" + id + "\t" + IdUtil.simpleUUID();
    }
}
