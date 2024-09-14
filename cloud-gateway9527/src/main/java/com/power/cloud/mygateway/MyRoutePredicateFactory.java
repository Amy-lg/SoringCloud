package com.power.cloud.mygateway;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 作用：自定义网关配置类
 * 需求说明：自定义配置会员等级userType，在yml中配置以适配是否可以访问
 * @author Amy
 * @since 2024/08/08
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    // 配置yml支持简介配置格式的方法
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 检查request的参数里面，userType是否为指定的值
                // 访问地址中必须带有userType参数，即：http://localhost:9527/pay/gateway/get/1?userType=gold
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if (userType == null) {
                    return false;
                }
                // 这里的config中的值就是yml配置的值
                if (userType.equalsIgnoreCase(config.getUserType())) {
                    return true;
                }
                return false;
            }
        };
    }

    @Validated
    public static class Config {
        private @NotNull String userType;

        public Config() {
        }

        public String getUserType() {
            return userType;
        }
        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
