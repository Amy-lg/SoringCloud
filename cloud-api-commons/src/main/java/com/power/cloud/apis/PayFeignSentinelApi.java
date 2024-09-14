package com.power.cloud.apis;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.power.cloud.fallback.PayFeignSentinelApiFallback;
import com.power.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 作用：OpenFeign整合Sentinel接口，书写统一的fallback异常返回公共方法
 * @author Amy
 * @since 2024年9月9日
 */
@FeignClient(value = "nacos-provider-payment", fallback = PayFeignSentinelApiFallback.class)
public interface PayFeignSentinelApi {

    // OpenFeign整合Sentinel进行流量降级和服务监控
    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
