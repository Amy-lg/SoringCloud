package com.power.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

/**
 * 作用：统一返回枚举类，表明返回的状态信息
 * 定义一个枚举类的步骤：举值-构造-遍历
 * @author Amy
 * @since 2024/07/26
 */
@Getter
public enum ResultCodeEnum {

    // 1.举值
    RC999("999", "operation failed"),
    RC200("200", "success"),
    RC201("201", "服务器开启降级保护"),
    RC202("202", "热点参数限流，请稍后重试"),
    RC203("203", "系统规则不满足要求"),
    RC204("204", "授权规则不通过"),
    RC403("403", "无权限访问，请联系管理员"),
    RC404("404", "404NOT FOUND"),
    RC401("401", "匿名用户访问无权限异常"),
    RC500("500", "系统异常，请稍后重试"),
    RC375("375", "数学运算异常"),

    INVALID_TOKEN("2001", "访问令牌不合法"),
    ACCESS_DENIED("2003", "没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED("1001", "客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR("1002", "用户名或密码错误"),
    BUSINESS_ERROR("1004", "业务逻辑异常"),
    UNSUPPORTED_GRANT_TYPE("1003", "不支持的认证模式");

    // 2.构造
    private final String code;  // 自定义状态码
    private final String message;  // 自定义描述信息

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 3.遍历
    // 3.1 传统版
    public static ResultCodeEnum getResultCodeEnum(String code) {
        for (ResultCodeEnum element : ResultCodeEnum.values()) {
            if (element.getCode().equalsIgnoreCase(code)) {
                return element;
            }
        }
        return null;
    }

    // 3.2 流式版
    public static ResultCodeEnum getResultCodeEnumByStream(String code) {
        return Arrays.stream(ResultCodeEnum.values())
                .filter(x -> x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
