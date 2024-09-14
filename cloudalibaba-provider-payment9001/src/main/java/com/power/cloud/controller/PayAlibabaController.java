package com.power.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.power.cloud.entities.PayDTO;
import com.power.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Nacos 控制层
 * @author Amy
 * @since 2024/08/13
 */
@RestController
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return "Nacos registry,serverPort=" + serverPort + "\t,id=" + id;
    }

    // openfeign+sentinel进行流量降级和服务监控整合处理
    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") Integer orderNo) {
        return ResultData.success(new PayDTO());
    }
    public ResultData handlerBlockHandler(@PathVariable("orderNo") Integer orderNo, BlockException blockException) {
        return ResultData.fail("500", "getPayByOrderNo服务不可用，" + "触发sentinel流控规则");
    }
}