package com.power.cloud.apis;

import com.power.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 账户Feign接口
 * @author Amy
 * @since 2024年9月12日
 */
@FeignClient(value = "seata_account_service")
public interface AccountFeignApi {
    // 扣减账户余额
    @PostMapping("/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
