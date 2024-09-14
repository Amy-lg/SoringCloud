package com.power.cloud.apis;

import com.power.cloud.entities.PayDTO;
import com.power.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);

    @GetMapping("/pay/get/info")
    String myLoadBalancer();

    // 熔断验证接口
    @GetMapping("/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    // 信号量/线程池 舱壁隔离验证接口
    @GetMapping("/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    // resilience4j RateLimit限流
    @GetMapping("/pay/rateLimit/{id}")
    String myRateLimit(@PathVariable("id") Integer id);

    // Gateway网关测试接口1
    @GetMapping("/pay/gateway/get/{id}")
    ResultData getByIdGateway(@PathVariable("id") Integer id);

    // Gateway网关测试接口2
    @GetMapping("/pay/gateway/info")
    ResultData<String> getGatewayInfo();


}
