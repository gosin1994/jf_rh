package com.jt.dao;

import java.util.List;

import com.jt.entity.CommissionRule;

public interface CommissionRuleDao {
	
	
	CommissionRule selectByLevel(Integer level);
	
	List<CommissionRule> selectAll();
	
	
	int updateByLevel(CommissionRule record);
	
    int insert(CommissionRule record);

    int insertSelective(CommissionRule record);
}