package com.jt.dao;

import com.jt.entity.Customer;
import com.jt.entity.Member;

public interface CustomerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);
    
    Customer selectByPhone(String phone);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}