package com.jt.service;

import com.jt.entity.Customer;

public interface CustomerService {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

	boolean apply(Customer customer);

	boolean isPhoneExist(String phone);
}