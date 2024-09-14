package com.power.cloud.controller;

import com.power.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * 作用：测试断路器控制类
 * @author Amy
 * @since 2024/08/02
 */
@RestController
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;

    /**
     * @CircuitBreaker:服务熔断注解
     *  name属性：指定分布式模块
     *  fallbackMethod属性：熔断后降级处理方法
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    // 此方法就是服务降级后的兜底处理方法
    public String myCircuitFallback(Integer id, Throwable t) {
        return "系统繁忙，请稍后再试";
    }

    /**
     * 信号量舱壁隔离测试方法
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    public String myBulkheadFallback(Integer id, Throwable t) {
        return "信号量舱壁测试---隔板超出最大数量限制，系统繁忙，请稍后再试！！！";
    }

    /**
     * 线程池舱壁隔离测试方法
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/threadPool/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myFallbackThreadPool", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id") Integer id) {
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + "THREADPOOL");
    }
    public CompletableFuture<String> myFallbackThreadPool(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "线程池舱壁测试---隔板超出最大数量限制，系统繁忙，请稍后再试！！！");
    }

    /**
     * 限流测试方法
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateLimitFallback")
    public String myBulkheadRateLimiter(@PathVariable("id") Integer id) {
        return payFeignApi.myRateLimit(id);
    }
    public String myRateLimitFallback(Integer id, Throwable t) {
        return "你被限流了，请稍后访问";
    }
}
