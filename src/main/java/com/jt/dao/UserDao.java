package com.jt.dao;

import java.util.List;

import com.jt.common.page.Page;
import com.jt.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAll(User query, Page<User> page);

	User selectByPhone(String phone);
}