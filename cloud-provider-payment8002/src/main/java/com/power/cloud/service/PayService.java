package com.power.cloud.service;

import com.power.cloud.entities.Pay;

import java.util.List;

/**
 * 这里不用添加 @Mapper 注解，因为在主启动类上添加了 @MapperScan 注解
 * @author Amy
 * @since 2024/07/25
 */
public interface PayService {
    int add(Pay pay);
    int delete(Integer id);
    int update(Pay pay);
    Pay getById(Integer id);
    List<Pay> getAll();
}
