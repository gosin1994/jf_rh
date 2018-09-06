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

import com.alibaba.fastjson.JSON;
import com.jt.common.page.Page;
import com.jt.common.util.SystemUtil;
import com.jt.entity.Commission;
import com.jt.entity.Member;
import com.jt.entity.Node;
import com.jt.entity.User;
import com.jt.service.MemberService;

@Controller
@RequestMapping("/i/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Member query, Page<Member> page){
		ModelAndView mv = new ModelAndView();
		User user = SystemUtil.getLoginUser(request);
		List<Member> members = null;
		if(user.getIsAdmin()==1){
			members = memberService.selectChildMembers(query, page, null);
		}else {
			String phone = user.getPhone();
			members = memberService.selectChildMembers(query, page, phone);
		}
		
		
		mv.addObject("query", query);
		mv.addObject("page", page);
		mv.addObject("members", members);
		mv.setViewName("i/member_list");
		return mv;
	}
	
	
	@RequestMapping("/{id}")
	public ModelAndView view(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView();
		Member member =  memberService.selectByPrimaryKey(id);
		mv.addObject("member", member);
		mv.setViewName("i/member_edit");
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ModelAndView persist(HttpServletRequest request, Member member){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/i/member/");
		
		/*if(!SystemUtil.isAdmin(request)){
			return mv;
		}*/
		
		memberService.persist(member);
		
		return mv;
	}
	
	
	@RequestMapping(value="{memberId}/commissions",method=RequestMethod.GET)
	public ModelAndView getCommissionList(@PathVariable Integer memberId, Page<Commission> page){
		ModelAndView mv = new ModelAndView();
		
		List<Commission> commissions = memberService.getCommissionList(memberId,page);
		Member member =  memberService.selectByPrimaryKey(memberId);
		mv.addObject("member", member);
		mv.addObject("commissions", commissions);
		mv.setViewName("i/member_commissions");
		return mv;
	}
	
	
	@RequestMapping(value="hierarchy")
	public ModelAndView hierarchy(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		User user = SystemUtil.getLoginUser(request);
		List<Node> memberTreeData = null;
		if(user.getIsAdmin()==1){
			memberTreeData = memberService.buildChildMemberTree(null);
		}else {
			String phone = user.getPhone();
			memberTreeData = memberService.buildChildMemberTree(phone);
		}
		mv.addObject("memberData", JSON.toJSONString(memberTreeData));
		mv.setViewName("i/member_hierarchy");
		return mv;
	}
	

	
}
