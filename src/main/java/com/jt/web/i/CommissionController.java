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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jt.common.page.Page;
import com.jt.common.util.SystemUtil;
import com.jt.entity.Apply;
import com.jt.entity.Commission;
import com.jt.entity.CommissionRule;
import com.jt.service.ApplyService;
import com.jt.service.CommissionService;

@Controller
@RequestMapping("/i/commission")
public class CommissionController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommissionController.class);
	
	@Autowired
	private CommissionService commissionService;
	
	
	@RequestMapping
	public ModelAndView list(Commission query, Page<Commission> page){
		ModelAndView mv = new ModelAndView();
		
		List<Commission> commissions = commissionService.selectAll(query,page);
		mv.addObject("query", query);
		mv.addObject("page", page);
		mv.addObject("commissions", commissions);
		mv.setViewName("i/commission_list");
		return mv;
	}
	
	
	
	@RequestMapping("/pay")
	public ModelAndView pay(@RequestParam("id") Integer id){
		ModelAndView mv = new ModelAndView();
		
		commissionService.pay(id);
		mv.setViewName("redirect:/i/commission");
		return mv;
	}
	
	
	
	
	
	@RequestMapping(value="/rule")
	public ModelAndView listRule(){
		ModelAndView mv = new ModelAndView();
		
		List<CommissionRule> commissionRules = commissionService.selectAllRule();
		mv.addObject("commissionRules", commissionRules);
		mv.setViewName("i/commission_rule_list");
		return mv;
	}
	
	@RequestMapping(value="/rule/{level}")
	public ModelAndView listRule(@PathVariable Integer level){
		ModelAndView mv = new ModelAndView();
		
		CommissionRule commissionRule = commissionService.selectRuleByLevel(level);
		mv.addObject("commissionRule", commissionRule);
		mv.setViewName("i/commission_rule_edit");
		return mv;
	}
	
	
	@RequestMapping(value="/rule/{level}",method=RequestMethod.POST)
	public ModelAndView persistRule(CommissionRule commissionRule){
		ModelAndView mv = new ModelAndView();
		
		commissionService.persistRule(commissionRule);
		mv.setViewName("redirect:/i/commission/rule");
		return mv;
	}
	
	
	
	

	
}
