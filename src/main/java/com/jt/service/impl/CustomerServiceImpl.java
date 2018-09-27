package com.jt.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.dao.ApplyDao;
import com.jt.dao.CustomerDao;
import com.jt.dao.KeywordDao;
import com.jt.dao.MemberDao;
import com.jt.entity.Apply;
import com.jt.entity.Customer;
import com.jt.entity.Member;
import com.jt.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ApplyDao applyDao;
	
	@Autowired
	private KeywordDao keywordDao;
	

	public Customer selectByPrimaryKey(Integer id) {
		return customerDao.selectByPrimaryKey(id);
	}
	
	
	public boolean apply(Customer customer,String url) {
		

		customer.setCreateTime(new Date());
		customer.setUpdateTime(customer.getCreateTime());
		
		Member samePhonemember = memberDao.selectByPhone(customer.getPhone());
		if(samePhonemember!=null){
			customer.setIsMember(1);
		}else {
			customer.setIsMember(0);
		}
		
		customerDao.insert(customer);
		
		
		//根据url查询关键词
		String findKeyword = keywordDao.selectKeywordByUrl(url);
		if (findKeyword == null) {
			findKeyword = "无关键词";
		} 
		
		System.out.println("进入了 CustomerServiceImpl apply 中 keyword======"+findKeyword);
		
		Apply apply = new Apply();
		
		
		apply.setName(customer.getName());
		apply.setPhone(customer.getPhone());
		apply.setAge(customer.getAge());
		apply.setInsureYear(customer.getInsureYear());
		apply.setEducationLevel(customer.getEducationLevel());
		apply.setCustomerId(customer.getId());
		apply.setIsMember(customer.getIsMember());
		apply.setMemberId(customer.getMemberId());
		
		//把查询到的keyword保存到apply中
		apply.setKeyword(findKeyword);
		
		if(customer.getMemberId()!=0){
			
			Member member = memberDao.selectByPrimaryKey(customer.getMemberId());
			apply.setMemberName(member.getName());
			apply.setRootMemberId(member.getRootMemberId());
			apply.setRootMemberName(member.getRootMemberName()); 
		}
		
		apply.setState(0);
		
		apply.setCreateTime(customer.getCreateTime());
		apply.setUpdateTime(apply.getCreateTime());
		
		int number = applyDao.insert(apply);
		if (number > 0) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	
	public int insert(Customer record) {
		// TODO Auto-generated method stub
		return customerDao.insert(record);
	}



	@Override
	public boolean isPhoneExist(String phone) {
		Customer dbCustomer = customerDao.selectByPhone(phone);
		return dbCustomer!=null;
	}

	
	
	
	

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}



	public int insertSelective(Customer record) {
		// TODO Auto-generated method stub
		return 0;
	}



	



	public int updateByPrimaryKeySelective(Customer record) {
		// TODO Auto-generated method stub
		return 0;
	}



	public int updateByPrimaryKey(Customer record) {
		// TODO Auto-generated method stub
		return 0;
	}






	




	
	
}
