package com.jt.service;

import java.util.List;

import com.jt.common.page.Page;
import com.jt.entity.Apply;
import com.jt.entity.User;

public interface ApplyService {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

	List<Apply> selectAll(Apply query, Page<Apply> page);

	void sign(Integer id, User user);

	List<Apply> selectChildApply(Apply query, Page<Apply> page, String phone);
}