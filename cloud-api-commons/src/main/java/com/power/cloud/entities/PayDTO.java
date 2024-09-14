package com.power.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * dto层表示不能够与数据库中的字段一一对应，避免数据泄露;
 * 一般而言，调用者不能知道服务提供者的entities资源以及表结构关系，所以
 *    服务提供方给出接口文档都应当是DTO;
 * @author Amy
 * @since 2024/07/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable {
    private Integer id;
    // 支付流水号
    private String payNo;
    // 订单号
    private String orderNo;
    // 用户id
    private Integer userId;
    // 交易金额
    private BigDecimal amount;
}
