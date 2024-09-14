package com.power.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger3配置类
 * @author Amy
 * @since 2024/07/25
 */
@Configuration
public class Swagger3Config {

    /**
     * 支付模块
     * @return
     */
    @Bean
    public GroupedOpenApi PayApi() {
        return GroupedOpenApi.builder()
                .group("支付微服务模块")
                .pathsToMatch("/pay/**")
                .build();
    }

    /**
     * 其他模块
     * @return
     */
    @Bean
    public GroupedOpenApi OtherApi() {
        return GroupedOpenApi.builder()
                .group("其他微服务模块")
                .pathsToMatch("/other/**", "/others")
                .build();
    }

    /**
     * 文档的描述说明
     * @return
     */
    public OpenAPI docsOpenApi() {
        return new OpenAPI()
                .info(new Info().title("cloud2024")
                        .description("通用设计rest")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.hong.com")
                        .url("https://www.baidu.com/"));
    }
}
