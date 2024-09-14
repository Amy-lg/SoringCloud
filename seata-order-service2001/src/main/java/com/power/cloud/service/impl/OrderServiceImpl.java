package com.power.cloud.service.impl;

import com.power.cloud.apis.AccountFeignApi;
import com.power.cloud.apis.StorageFeignApi;
import com.power.cloud.entities.Order;
import com.power.cloud.mapper.OrderMapper;
import com.power.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    // 订单微服务通过OpenFeign调用库存微服务、账户微服务
    @Resource
    private StorageFeignApi storageFeignApi;
    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
    public void createOrder(Order order) {
        // 获取xid全局事务id的检查
        String xid = RootContext.getXID();
    }
}
