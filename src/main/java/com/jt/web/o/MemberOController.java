package com.jt.web.o;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jt.common.Constant;
import com.jt.common.page.Page;
import com.jt.common.util.SystemUtil;
import com.jt.entity.Commission;
import com.jt.entity.Customer;
import com.jt.entity.Member;
import com.jt.entity.Node;
import com.jt.entity.User;
import com.jt.service.CustomerService;
import com.jt.service.MemberService;

@Controller
@RequestMapping("/o/member")
public class MemberOController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberOController.class);
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private CustomerService customerService;
	
/*	@RequestMapping("/")
	public ModelAndView index(@RequestParam("mid") Integer memberId){
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberId", memberId);
		mv.setViewName("o/member");
		return mv;
	}*/
	
	
	@RequestMapping("/apply")
	public ModelAndView applyView(@RequestParam(value="mid", defaultValue="0") Integer mid,@RequestParam(value="cid", defaultValue="0") Integer cid){
		ModelAndView mv = new ModelAndView();
		mv.addObject("mid", mid);
		mv.addObject("cid", cid);
		if(cid!=0){
			Customer customer = customerService.selectByPrimaryKey(cid);
			mv.addObject("customer", customer);
			mv.addObject("mid", customer.getMemberId());
		}
		mv.setViewName("o/member");
		return mv;
	}
	
	
	
	/**
	 * 会员注册成功页面
	 * @param request
	 * @param member
	 * @return
	 */
	@RequestMapping(value="/apply",method=RequestMethod.POST)
	public ModelAndView apply(HttpServletRequest request, Member member){
		ModelAndView mv = new ModelAndView();
		memberService.apply(member);
		mv.addObject("member", member);
		mv.setViewName("o/m_success");
		return mv;
	}
	
	
	
	/**
	 * 会员登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView loginView(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("o/member_login");
		return mv;
	}
	
	

	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, String phone,String name){
		try{
			String msg = memberService.login(request, phone, name);
			if("success".equals(msg)){
				
				return "redirect:/o/member/info";
			}else {
				return "redirect:/o/member/login";
			}
		}catch(Exception e){
			logger.error("member {} {} login error!",phone,name,e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String doLogin(HttpServletRequest request){
		memberService.logout(request);
		return "redirect:/o/member/login";
	}
	
	
	@RequestMapping(value="info", method=RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		Member loginMember =  SystemUtil.getLoginMember(request);
		if(loginMember==null){
			mv.setViewName("redirect:/o/member/login");
			return mv;
		}
		mv.addObject("member", loginMember);
		mv.setViewName("o/member_info");
		
		/*List<Node> memberTreeData = null;
		memberTreeData = memberService.buildChildMemberTree(phone);
		mv.addObject("memberData", JSON.toJSONString(memberTreeData));
		mv.setViewName("o/member_hierarchy");*/
		return mv;
	}
	
	@RequestMapping(value="{memberId}/commissions",method=RequestMethod.GET)
	public ModelAndView getCommissionList(@PathVariable Integer memberId, Page<Commission> page){
		ModelAndView mv = new ModelAndView();
		
		List<Commission> commissions = memberService.getCommissionList(memberId,page);
		Member member =  memberService.selectByPrimaryKey(memberId);
		mv.addObject("member", member);
		mv.addObject("commissions", commissions);
		mv.addObject("count", commissions.size());
		mv.setViewName("o/member_commissions");
		return mv;
	}
	
	@RequestMapping(value="{memberId}/hierarchy", method=RequestMethod.GET)
	public ModelAndView getMemberHierarchy(HttpServletRequest request,@PathVariable Integer memberId, Page<Commission> page){
		ModelAndView mv = new ModelAndView();
		Member loginMember =  SystemUtil.getLoginMember(request);
		if(loginMember==null){
			mv.setViewName("redirect:/o/member/login");
			return mv;
		}
		List<Node> memberTreeData = null;
		memberTreeData = memberService.buildChildMemberTree(loginMember.getPhone());
		mv.addObject("memberData", JSON.toJSONString(memberTreeData));
		mv.setViewName("o/member_hierarchy");
		return mv;
	}
	
	@RequestMapping("/isPhoneExist")
	@ResponseBody
	public boolean isPhoneExist(String phone){
		return memberService.isPhoneExist(phone);
	}
	
}
