package com.jt.service;

import java.util.List;

import com.jt.common.page.Page;
import com.jt.entity.Commission;
import com.jt.entity.CommissionRule;

public interface CommissionService {
    int deleteByPrimaryKey(Integer id);

    int insert(Commission record);

    int insertSelective(Commission record);

    Commission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Commission record);

    int updateByPrimaryKey(Commission record);

	List<Commission> selectAll(Commission query, Page<Commission> page);

	void pay(Integer id);

	List<CommissionRule> selectAllRule();

	CommissionRule selectRuleByLevel(Integer level);

	int persistRule(CommissionRule commissionRule);

	void persist(Commission commission);
}