package com.power.cloud.controller;

import com.power.cloud.apis.PayFeignSentinelApi;
import com.power.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者微服务模块控制层
 * @author Amy
 * @since 2024年8月13日
 */
@RestController
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/customer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        String result = restTemplate.getForObject(serverUrl + "/pay/nacos" + id, String.class);
        return result + "\t" + "order83消费者微服务模块";
    }

    // 测试Sentinel整合OpenFeign
    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping("/consumer/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }
}
