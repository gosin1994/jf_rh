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
import com.jt.entity.Apply;
import com.jt.entity.Member;
import com.jt.entity.User;
import com.jt.service.ApplyService;

@Controller
@RequestMapping("/i/apply")
public class ApplyController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyController.class);
	
	@Autowired
	private ApplyService applyService;
	
	
	@RequestMapping
	public ModelAndView list(HttpServletRequest request, Apply query, Page<Apply> page){
		ModelAndView mv = new ModelAndView();
		User user = SystemUtil.getLoginUser(request);
		List<Apply> applies = null;
		if(user.getIsAdmin()==1){
			applies = applyService.selectAll(query,page);
		}else {
			String phone = user.getPhone();
			applies = applyService.selectChildApply(query, page, phone);
		}
		
		
		mv.addObject("query", query);
		mv.addObject("page", page);
		mv.addObject("applies", applies);
		mv.setViewName("i/apply_list");
		return mv;
	}
	
	
	@RequestMapping("/sign")
	public ModelAndView sign(@RequestParam("id") Integer id,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		applyService.sign(id, SystemUtil.getLoginUser(request));
		mv.setViewName("redirect:/i/apply");
		return mv;
	}
	
	
	
	

	
}
