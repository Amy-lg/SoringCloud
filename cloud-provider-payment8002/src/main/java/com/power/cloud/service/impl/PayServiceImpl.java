package com.power.cloud.service.impl;

import com.power.cloud.entities.Pay;
import com.power.cloud.mapper.PayMapper;
import com.power.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层CRUD
 * @author Amy
 * @since 2024/07/25
 */
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay) {
        // insertSelective() 此方法表示插入的实体中null属性不会保存，会使用数据库中设置的默认值
        // insert() 此方法表示null的属性会保存，不会使用数据库的默认值
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        // 此方法表示按照主键删除
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
