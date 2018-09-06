package com.jt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.page.Page;
import com.jt.dao.CommissionDao;
import com.jt.dao.CommissionRuleDao;
import com.jt.entity.Commission;
import com.jt.entity.CommissionRule;
import com.jt.service.CommissionService;

@Service("commissionService")
public class CommissionServiceImpl implements CommissionService {
	
	

	
	@Autowired
	private CommissionDao commissionDao;
	
	@Autowired
	private CommissionRuleDao commissionRuleDao;
	
	
	
	public List<Commission> selectAll(Commission query, Page<Commission> page) {
		return commissionDao.selectAll(query, page);
	}
	
	@Override
	public void pay(Integer id) {
		Commission commission = commissionDao.selectByPrimaryKey(id);
		commission.setState(1);
		commission.setUpdateTime(new Date());
		commissionDao.updateByPrimaryKey(commission);
		
		
	}
	
	
	
	
	@Override
	public List<CommissionRule> selectAllRule() {
		return commissionRuleDao.selectAll();
	}

	@Override
	public CommissionRule selectRuleByLevel(Integer level) {
		return commissionRuleDao.selectByLevel(level);
	}

	@Override
	public int persistRule(CommissionRule commissionRule) {
		return commissionRuleDao.updateByLevel(commissionRule);
	}
	
	
	
	

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Commission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Commission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Commission selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Commission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Commission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void persist(Commission commission) {

		/*Commission dbcommission = commissionDao.selectByPrimaryKey(commission.getId());
		
		dbcommission.setAmount(commission.getAmount());
		dbcommission.setUpdateTime(new Date());
		
		commissionDao.updateByPrimaryKey(dbcommission);*/
		
		
	}

	






	
	
	
	
	

	
	
}
