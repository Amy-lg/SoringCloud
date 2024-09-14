package com.power.cloud.controller;

import com.power.cloud.apis.PayFeignApi;
import com.power.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作用：feign80订单控制层调用的是common微服务模块的PayFeignApi接口中的方法
 * @author Amy
 * @since 2024/08/07
 */
@RestController
public class OrderGatewayController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/gateway/get/{id}")
    public ResultData getByIdGateway(@PathVariable("id") Integer id) {
        return payFeignApi.getByIdGateway(id);
    }

    @GetMapping("/feign/pay/gateway/info")
    public ResultData<String> getGatewayInfo(){
        return payFeignApi.getGatewayInfo();
    }
}
