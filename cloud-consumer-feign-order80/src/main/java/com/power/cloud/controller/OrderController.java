package com.power.cloud.controller;

import com.power.cloud.apis.PayFeignApi;
import com.power.cloud.entities.PayDTO;
import com.power.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 订单控制层
 * @author Amy
 * @since 2024/07/26
 */
@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    @GetMapping("/feign/pay/mylb")
    public String mylb() {
        return payFeignApi.myLoadBalancer();
    }
}
