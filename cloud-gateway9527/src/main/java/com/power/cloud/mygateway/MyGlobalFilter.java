package com.power.cloud.mygateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 作用：自定义Gateway全局过滤器，实现指定需求
 * @author Amy
 * @since 2024/08/09
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    private static Logger logger = LoggerFactory.getLogger(MyGlobalFilter.class);
    private static final String BEGIN_VISIT_TIME = "begin_visit_time";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.先记录下访问接口开始的时间
        Map<String, Object> attributesMap = exchange.getAttributes();
        attributesMap.put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        // 2.返回统计的结果到后台
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (beginVisitTime != null) {
                logger.info("访问接口主机：" + exchange.getRequest().getURI().getHost());
                logger.info("访问接口端口：" + exchange.getRequest().getURI().getPort());
                logger.info("访问接口URL：" + exchange.getRequest().getURI().getPath());
                logger.info("访问接口URL后面的参数：" + exchange.getRequest().getURI().getRawQuery());
                logger.info("访问接口时长：" + (System.currentTimeMillis() - beginVisitTime) + "ms");
                logger.info("===================分割线====================");
                System.out.println();
            }
        }));
    }

    /**
     * 数字越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
