package com.jt.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.dao.ApplyDao;
import com.jt.dao.ApplyDao2;
import com.jt.dao.EduCustomerDao;
import com.jt.dao.MemberDao;
import com.jt.entity.Apply;
import com.jt.entity.Apply2;
import com.jt.entity.EduCustomer;
import com.jt.entity.Member;
import com.jt.service.EduCustomerService;

@Service("eduCustomerService")
public class EduCustomerServiceImpl implements EduCustomerService {

	
	@Autowired
	private EduCustomerDao eduCustomerDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ApplyDao2 applyDao2;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(EduCustomer record) {
		return eduCustomerDao.insert(record);
	}

	@Override
	public int insertSelective(EduCustomer record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EduCustomer selectByPrimaryKey(Integer id) {
		return eduCustomerDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EduCustomer record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(EduCustomer record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void apply2(EduCustomer eduCustomer) {
		eduCustomer.setCreateTime(new Date());
		eduCustomer.setUpdateTime(eduCustomer.getCreateTime());
		
		Member samePhonemember = memberDao.selectByPhone(eduCustomer.getPhone());
		if(samePhonemember!=null){
			eduCustomer.setIsMember(1);
		}else {
			eduCustomer.setIsMember(0);
		}
		
		eduCustomerDao.insert(eduCustomer);
		
		//Apply apply = new Apply();
		Apply2 apply = new Apply2();
		
		apply.setName(eduCustomer.getName());
		apply.setPhone(eduCustomer.getPhone());
		apply.setAge(eduCustomer.getAge());
		
		apply.setEducationLevel(eduCustomer.getEducationLevel());
		apply.setEnterEducationLevel(eduCustomer.getEnterEducationLevel());
		
		apply.setMemberId(eduCustomer.getMemberId());
		apply.setIsMember(eduCustomer.getIsMember());
		apply.setCustomerId(eduCustomer.getId());
		
		if (eduCustomer.getMemberId() != 0) {
			Member member = memberDao.selectByPrimaryKey(eduCustomer.getMemberId());
			apply.setMemberName(member.getName());
			apply.setRootMemberId(member.getRootMemberId());
			apply.setRootMemberName(member.getRootMemberName());
		}
		apply.setState(0);
		
		apply.setCreateTime(eduCustomer.getCreateTime());
		apply.setUpdateTime(apply.getCreateTime());
		
		applyDao2.insert(apply);
	}

	@Override
	public boolean isPhoneExist(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

}
