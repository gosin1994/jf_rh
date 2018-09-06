package com.jt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.page.Page;
import com.jt.entity.Apply2;

public interface ApplyDao2 {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply2 record);

    int insertSelective(Apply2 record);

    Apply2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply2 record);

    int updateByPrimaryKey(Apply2 record);

	List<Apply2> selectAll(@Param("query")Apply2 query, Page<Apply2> page);

	int updateMemberStateByCustomerId(Integer customerId);
}