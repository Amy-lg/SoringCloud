package com.power.cloud.fallback;

import com.power.cloud.apis.PayFeignSentinelApi;
import com.power.cloud.resp.ResultData;
import org.springframework.stereotype.Component;

/**
 * PayFeignSentinelApi接口异常时，fallback属性值统一回调此类中的公共异常处理方法
 * @author Amy
 * @since 2024年9月9日
 */
@Component
public class PayFeignSentinelApiFallback implements PayFeignSentinelApi {
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail("500", "服务器繁忙，请稍后再试！");
    }
}
