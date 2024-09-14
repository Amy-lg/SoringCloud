package com.power.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.power.cloud.entities.Pay;
import com.power.cloud.entities.PayDTO;
import com.power.cloud.resp.ResultData;
import com.power.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层
 * @author Amy
 * @since 2024/07/25
 */
@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付的CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @Operation(summary = "新增", description = "新增支付流水方法，json串作为参数")
    @PostMapping("/pay/add")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        int add = payService.add(pay);
        return ResultData.success("成功插入记录，返回值" + add);
    }

    @Operation(summary = "删除", description = "删除支付流水方法，通过id删除")
    @DeleteMapping("/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int delete = payService.delete(id);
        return ResultData.success(delete);
    }

    @Operation(summary = "修改", description = "修改支付流水方法")
    @PutMapping("/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtil.copyProperties(payDTO, pay);
        int update = payService.update(pay);
        return ResultData.success("成功修改记录，返回值" + update);
    }

    @Operation(summary = "查询", description = "查询支付流水方法")
    @GetMapping("/pay/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("id不能为负数");
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @Operation(summary = "查询全部", description = "查询全部支付流水方法")
    @GetMapping("/pay/getAll")
    public ResultData<List<Pay>> getAll() {
        List<Pay> all = payService.getAll();
        return ResultData.success(all);
    }


    @Value("${server.port}")
    private String port;
    @GetMapping("/pay/get/info")
    public String getConfigInfo(@Value("${power.info}") String configInfo) {
        return "configInfo:" + configInfo + "--port:" + port;
    }
}
