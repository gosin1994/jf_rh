package com.jt.service;

import java.util.List;

import com.jt.common.page.Page;
import com.jt.entity.Apply2;
import com.jt.entity.User;

public interface ApplyService2 {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply2 record);

    int insertSelective(Apply2 record);

    Apply2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply2 record);

    int updateByPrimaryKey(Apply2 record);

	List<Apply2> selectAll(Apply2 query, Page<Apply2> page);

	void sign(Integer id, User user);

	List<Apply2> selectChildApply(Apply2 query, Page<Apply2> page, String phone);
}