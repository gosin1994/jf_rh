package com.jt.web.i;

import java.util.List;

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
import com.jt.entity.Apply;
import com.jt.entity.Commission;
import com.jt.entity.Order;
import com.jt.service.ApplyService;
import com.jt.service.OrderService;

@Controller
@RequestMapping("/i/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping
	public ModelAndView list(Order query, Page<Order> page){
		ModelAndView mv = new ModelAndView();
		
		List<Order> orders = orderService.selectAll(query, page);
		
		mv.addObject("orders", orders);
		mv.setViewName("i/order_list");
		return mv;
	}
	
	
	
	@RequestMapping(value="commissions/{orderId}",method=RequestMethod.GET)
	public ModelAndView getCommissionList(@PathVariable Integer orderId){
		ModelAndView mv = new ModelAndView();
		
		List<Commission> commissions = orderService.getCommissionList(orderId);
		
		mv.addObject("commissions", commissions);
		mv.setViewName("i/order_commissions");
		return mv;
	}

	
	
	@RequestMapping(value="/{id}")
	public ModelAndView viewOrder(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView();
		
		Order order = orderService.selectByPrimaryKey(id);
		mv.addObject("order", order);
		mv.setViewName("i/order_edit");
		return mv;
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ModelAndView persistOrder(Order order){
		ModelAndView mv = new ModelAndView();
		
		orderService.updateByPrimaryKey(order);
		mv.setViewName("redirect:/i/order");
		return mv;
	}
}
