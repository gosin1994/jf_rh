package com.jt.dao;

import com.jt.entity.Customer;
import com.jt.entity.EduCustomer;

/**
 * 提升学历的客户dao
 * @author Administrator
 *
 */
public interface EduCustomerDao {

	int deleteByPrimaryKey(Integer id);

    int insert(EduCustomer record);

    int insertSelective(EduCustomer record);

    EduCustomer selectByPrimaryKey(Integer id);
    
    EduCustomer selectByPhone(String phone);

    int updateByPrimaryKeySelective(EduCustomer record);

    int updateByPrimaryKey(EduCustomer record);

}
