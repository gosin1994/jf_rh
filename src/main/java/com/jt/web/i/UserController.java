package com.jt.web.i;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jt.common.page.Page;
import com.jt.common.util.SystemUtil;
import com.jt.entity.Member;
import com.jt.entity.User;
import com.jt.service.UserService;

@Controller
@RequestMapping("/i/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping
	public ModelAndView index(User query, Page<User> page){
		ModelAndView mv = new ModelAndView();
		List<User> users = userService.selectAll(query,page);
		mv.addObject("query", query);
		mv.addObject("page", page);
		mv.addObject("users", users);
		mv.setViewName("i/user_list");
		return mv;
	}
	
	
	@RequestMapping("/{id}")
	public ModelAndView view(HttpServletRequest request,@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView();
		User user =  userService.selectByPrimaryKey(id);
		mv.addObject("editUser", user);
		mv.setViewName("i/user_edit");
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ModelAndView persist(HttpServletRequest request, User user){
		ModelAndView mv = new ModelAndView();
		userService.persist(user);
		mv.setViewName("redirect:/i/user");
		return mv;
	}
	
	
	
	
}
