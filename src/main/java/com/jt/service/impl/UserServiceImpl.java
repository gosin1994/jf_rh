package com.jt.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jt.common.Constant;
import com.jt.common.page.Page;
import com.jt.dao.UserDao;
import com.jt.entity.User;
import com.jt.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	

	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/*
	 * 系统后台用户登录
	 * (non-Javadoc)
	 * @see com.jt.service.UserService#login(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
	public String login(HttpServletRequest request, String phone,
			String password) {
		User loginUser = userDao.selectByPhone(phone);
		
		
		if(loginUser != null){
			if(md5PasswordEncoder.encodePassword(password, phone).equals(loginUser.getPassword())
					){
				HttpSession session = request.getSession();
				session.setAttribute(Constant.LOGIN_USER, loginUser);
				return "success";
			}else {
				return "incorrect";
			}
			
		}
		return "incorrect";
	}
	

	
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.LOGIN_USER);
		session.invalidate();
	}
	
	public List<User> selectAll(User query, Page<User> page) {
		// TODO Auto-generated method stub
		return userDao.selectAll(query, page);
	}
	
	public void persist(User user) {

		if(user.getId()==null){
			
			user.setPassword(md5PasswordEncoder.encodePassword(user.getPassword(), user.getPhone()));
			
			user.setIsAdmin(0);
			user.setCreateTime(new Date());
			user.setUpdateTime(user.getCreateTime());
			userDao.insert(user);
		}else {
			User dbuser = userDao.selectByPrimaryKey(user.getId());
			if(!dbuser.getPassword().equals(user.getPassword())){
				dbuser.setPassword(md5PasswordEncoder.encodePassword(user.getPassword(), user.getPhone()));
			}
			dbuser.setName(user.getName());
			dbuser.setUsername(user.getUsername());
			dbuser.setPhone(user.getPhone());
			user.setUpdateTime(new Date());
			userDao.updateByPrimaryKey(dbuser);
		}
		
	}
	
	
	public int insert(User record) {
		// TODO Auto-generated method stub
		return userDao.insert(record);
	}
	
	
	
	

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKey(record);
	}

	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User selectByPrimaryKey(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}



	


	
}
