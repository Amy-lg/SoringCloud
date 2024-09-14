package com.power.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @SentinelResource注解测试类
 * @author Amy
 * @since 2024年9月5日
 */
@RestController
@Slf4j
public class RateLimitController {

    /**
     * 默认限流提示测试
     * @return
     */
    @GetMapping("/rateLimit/byUrl")
    public String byUrl() {
        return "按照rest地址限流测试。";
    }

    /**
     * 通过注解@SentinelResource限流，自定义异常提示
     * @return
     */
    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResourceSentinelResource", blockHandler = "handlerBlockHandler")
    public String byResource() {
        return "按照资源名称SentinelResource限流测试。";
    }
    public String handlerBlockHandler(BlockException blockException) {
        return "服务不可用，触发了@SentinelResource注解。";
    }

    /**
     * 通过注解@SentinelResource限流，加fallback
     * @param p1
     * @return
     */
    @GetMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource", blockHandler = "doActionBlockHandler",
            fallback = "doActionFallback")
    public String doAction(@PathVariable("p1") Integer p1) {
        if (p1 == 0) {
            throw new RuntimeException("p1等于0直接异常！");
        }
        return "doAction";
    }
    public String doActionBlockHandler(@PathVariable("p1") Integer p1, BlockException e) {
        log.error("sentinel配置限流", e);
        return "sentinel配置自定义限流";
    }
    public String doActionFallback(@PathVariable("p1") Integer p1, Throwable throwable) {
        log.error("程序逻辑异常:{}", throwable);
        return "程序逻辑异常:" + throwable.getMessage();
    }


    //热点规则
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "deal-HotKey处理热点参数";
    }
    public String dealHotKey(String p1, String p2, BlockException blockException) {
        return "blockHandler参数处理-dealHotKey";
    }
}
