package com.jt.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.common.util.SystemUtil;
import com.jt.entity.User;



public class PrivilegeFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String requestURI = httpRequest.getRequestURI();
		if(requestURI.contains("/i/")){
			User loginUser = SystemUtil.getLoginUser(httpRequest);
			if(loginUser == null){
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
			/*String userType = loginUser.getUserType();
			List<String> permitURIList = PrivilegeConf.PRIVILEGE_MAP.get(userType);
			if(permitURIList != null){
				for (String uri : permitURIList) {
					if(requestURI.indexOf(uri)!= -1){
						chain.doFilter(request, response);
						return;
					}
				}
				
			}
			httpResponse.sendRedirect(httpRequest.getContextPath()+PrivilegeConf.HOMEPAGE_MAP.get(userType));*/
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
