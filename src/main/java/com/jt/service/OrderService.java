package com.jt.service;

import java.util.List;

import com.jt.common.page.Page;
import com.jt.entity.Commission;
import com.jt.entity.Order;

public interface OrderService {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	List<Order> selectAll(Order query, Page<Order> page);

	List<Commission> getCommissionList(Integer orderId);
}