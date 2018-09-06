package com.jt.web.i;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jt.common.page.Page;
import com.jt.common.util.SystemUtil;
import com.jt.entity.Apply2;
import com.jt.entity.User;
import com.jt.service.ApplyService2;

/**
 * 学历申请
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/i/apply2")
public class ApplyController2 {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyController2.class);
	
	@Autowired
	private ApplyService2 applyService2;
	
	
	@RequestMapping
	public ModelAndView list(HttpServletRequest request, Apply2 query, Page<Apply2> page){
		ModelAndView mv = new ModelAndView();
		User user = SystemUtil.getLoginUser(request);
		List<Apply2> applies = null;
		if(user.getIsAdmin()==1){
			applies = applyService2.selectAll(query,page);
		}else {
			String phone = user.getPhone();
			applies = applyService2.selectChildApply(query, page, phone);
		}
		
		
		mv.addObject("query", query);
		mv.addObject("page", page);
		mv.addObject("applies2", applies);
		mv.setViewName("i/apply2_list");
		return mv;
	}
	
	
	@RequestMapping("/sign")
	public ModelAndView sign(@RequestParam("id") Integer id,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		applyService2.sign(id, SystemUtil.getLoginUser(request));
		mv.setViewName("redirect:/i/apply2");
		return mv;
	}
	
	
	
	

	
}
