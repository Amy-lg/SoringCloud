package com.power.cloud.apis;

import com.power.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存Feign接口
 * @author Amy
 * @since 2024年9月12日
 */
@FeignClient(value = "seata_storage_service")
public interface StorageFeignApi {
    // 扣减库存
    @PostMapping("/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
