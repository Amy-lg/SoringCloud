package com.power.cloud.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.power.cloud.entities.Pay;
import com.power.cloud.resp.ResultData;
import com.power.cloud.service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

/**
 * 作用：Gateway测试控制层
 * @author Amy
 * @since 2024/08/07
 */
@RestController
public class PayGatewayController {

    @Resource
    private PayService payService;

    @GetMapping("/pay/gateway/get/{id}")
    public ResultData<Pay> getByIdGateway(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping("/pay/gateway/info")
    public ResultData<String> getGatewayInfo() {
        return ResultData.success("gateway info test:" + IdUtil.simpleUUID());
    }

    /**
     * Gateway Filter测试接口方法
     * @param request
     * @return
     */
    @GetMapping("/pay/gateway/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request) {
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headerVal = request.getHeader(headName);
            System.out.println("请求头：" + headName + "；请求头值：" + headerVal);
            if (headName.equalsIgnoreCase("X-Request-power1") ||
                    headName.equalsIgnoreCase("X-Request-power2")) {
                result = result + headName + "\t" + headerVal + "";
            }
        }
        return ResultData.success("getGatewayFilter 过滤器 test" + result + "\t" + DateUtil.now());
    }
}
