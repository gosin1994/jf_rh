package com.jt.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jt.common.Constant;
import com.jt.entity.Member;
import com.jt.entity.User;


public class SystemUtil {
	
	
	
	public static User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (User) session.getAttribute(Constant.LOGIN_USER);
	}
	
	public static Member getLoginMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (Member) session.getAttribute(Constant.LOGIN_MEMBER);
	}
	
	
	public static boolean isAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user =  (User) session.getAttribute(Constant.LOGIN_USER);
		
		if(user==null){
			return false;
		}
		
		return user.getIsAdmin() == Constant.USER_TYPE_ADMIN;
		
	}
	
}
