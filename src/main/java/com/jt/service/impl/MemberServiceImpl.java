package com.jt.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.jt.common.Constant;
import com.jt.common.page.Page;
import com.jt.common.util.QRCodeUtil;
import com.jt.dao.ApplyDao;
import com.jt.dao.CommissionDao;
import com.jt.dao.CustomerDao;
import com.jt.dao.MemberDao;
import com.jt.entity.Commission;
import com.jt.entity.Customer;
import com.jt.entity.Member;
import com.jt.entity.Node;
import com.jt.entity.User;
import com.jt.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ApplyDao applyDao;
	
	@Autowired
	private CommissionDao commissionDao;
	
	
	public void apply(Member member) {
		// TODO Auto-generated method stub
		
		
		member.setCreateTime(new Date());
		member.setLevel(1);
		member.setOrderCount(0);
		member.setUpdateTime(member.getCreateTime());
		
		memberDao.insert(member);
		
		
		//<mvc:resources mapping="/img/**" location="/resources/img/"/> 
		
		String name = member.getName();
		if(name.length()>2){
			name = name.substring(0, 1)+" * "+name.substring(name.length()-1);
		}else {
			name = name.substring(0, 1)+" * ";
		}
		String text = name;
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+name);
		//String text = "推荐人："+name;
		//String text = " ";
		
		String fileName = member.getPhone()+".png";
		String qrcodeUrl = "/zx/img/qrcode/"+member.getPhone()+".jpg";
		//http://www.szhukou.com.cn/zx/o/customer/apply?mid=2
		
		//二维码跳转到测评页面
		//String message = "http://www.szhukou.com.cn/zx/o/customer/apply?mid=44";
		String message = "http://www.szhukou.com.cn/zx/o/customer/apply?mid="+member.getId();
		//二维码跳转到首页
		//String message = "http://www.szhukou.com.cn/zx/o/customer?mid="+member.getId();
		
		//String message = "http://119.29.95.129/zx/o/customer?mid="+member.getId();
		
		//String message = "http://www.yibaishangwu.com:9090/zx/o/customer?mid="+member.getId();
		//String message = "http://192.168.31.52:8080/zx/o/customer?mid="+member.getId();
		
		//String message = "http://192.168.31.52:8080/zx/o/customer";
		
		String imgFolderDir = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") 
				+"/resources/img/qrcode";
		fileName = imgFolderDir+"/" + fileName;
		
		try {
			QRCodeUtil.generateQRCode(fileName, message, imgFolderDir, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		member.setQrcodeUrl(qrcodeUrl);
		
		
		//set rootMermber
		if(member.getPid()==0){
			member.setRootMemberId(member.getId());
			member.setRootMemberName(member.getName());
		}else {
			Member parentMember = memberDao.selectByPrimaryKey(member.getPid());
			member.setRootMemberId(parentMember.getRootMemberId());
			member.setRootMemberName(parentMember.getRootMemberName());
		}
		
		
		memberDao.updateByPrimaryKey(member);
		
		
		
		if(member.getCustomerId()!=0){
			
			Customer customer = customerDao.selectByPrimaryKey(member.getCustomerId());
			customer.setIsMember(1);
			customer.setMemberId(member.getId());
			customerDao.updateByPrimaryKey(customer);
			
			applyDao.updateMemberStateByCustomerId(customer.getId());
			
		}else {//先办理入户，再办理会员，手机号关联
			Customer customer = customerDao.selectByPhone(member.getPhone());
			
			if(customer!=null){
				
				customer.setIsMember(1);
				customer.setMemberId(member.getId());
				customerDao.updateByPrimaryKey(customer);
				
				applyDao.updateMemberStateByCustomerId(customer.getId());
			}
		}
		
	}
	

	
	//=========================================================================	
	/**
	 * 替换旧的二维码
	 */
	@Test
	public void markQrcode() {
		int[] idArr =new int[]{25};
		String[] nameArr = new String[]{"虞克同"};
		String[] phoneArr = new String[]{"18948777979"};
		
		for (int i = 0; i < 300; i++) {
			
			String name = nameArr[i];
			if(name.length()>2){
				name = name.substring(0, 1)+" * "+name.substring(2);
			}else {
				name = name.substring(0, 1)+" * ";
			}
			String text = name;
			
			String imgFolderDir = "D:/qrcode";
			
			int memberId = idArr[i];
			String message = "http://www.yibaishangwu.com:9090/zx/o/customer?mid="+memberId;
			
			String number = phoneArr[i];
			//System.out.println("D:/qrcode/"+number+".jpg");
			
			try {
				QRCodeUtil.generateQRCode("D:/qrcode/"+number+".png", message,imgFolderDir,text);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	//=========================================================================	
	
	
	
	
	
	
	
	
	@Override
	public List<Member> selectAll(Member query, Page<Member> page) {
		return memberDao.selectAll(query, page);
	}

	
	
	@Override
	public Map<String, Object> getInfo(String name, String phone) {
		Map<String, Object> info = new HashMap<String, Object>();
		Member member = memberDao.selectByPhone(phone);
		
		
		
		if(member!=null && member.getName().equals(name)){
			
			List<Commission> commissions = commissionDao.selectByMemberId(member.getId());
			info.put("member", member);
			info.put("commissions", commissions);
			
		}
		
		return info;
	}
	
	@Override
	public List<Commission> getCommissionList(Integer memberId, Page<Commission> page) {
		// TODO Auto-generated method stub
		return commissionDao.selectByMemberId(memberId);
	}
	

	@Override
	public void persist(Member member) {
		
		Member db_member = memberDao.selectByPrimaryKey(member.getId());
		
		if(db_member.getLevel()!=member.getLevel()){//级别变动
			int newLevel = member.getLevel();
			int orderCount = 0;
			if(newLevel==1){
				orderCount = 0;
			}else if(newLevel==2){
				orderCount = 1;
			}else if(newLevel==3){
				orderCount = 2;
			}else if(newLevel==4){
				orderCount = 4;
			}else if(newLevel==5){
				orderCount = 8;
			}else if(newLevel==6){
				orderCount = 15;
			}else if(newLevel==7){
				orderCount = 20;
			}
			db_member.setLevel(newLevel);
			db_member.setOrderCount(orderCount);
		}
		
		
		db_member.setAlipayNo(member.getAlipayNo());
		db_member.setBankName(member.getBankName());
		db_member.setBankAccountName(member.getBankAccountName());
		db_member.setBankAccountNo(member.getBankAccountNo());
		db_member.setUpdateTime(new Date());

		memberDao.updateByPrimaryKey(db_member);
	}
	
	
	
	
	
	
	

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Member record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Member record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Member selectByPrimaryKey(Integer id) {
		return memberDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Member record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Member record) {
		// TODO Auto-generated method stub
		return 0;
	}




	public static void main(String[] args) {
		String message = "http://www.szhukou.com.cn/zx/o/customer";
		//String message = "http://192.168.1.106:8080/zx/o/customer";
		//String message = "http://192.168.31.52:8080/zx/o/customer";

		try {
			QRCodeUtil.generateQRCode("D:/test/zxdir/init.png", message,"D:/test/zxdir","初始二维码");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public List<Node> buildChildMemberTree(String phone) {

		List<Node> memberData = new ArrayList<Node>();
		Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		
		List<Member> parentMembers = new ArrayList<Member>();
		List<Member> memberPool = memberDao.selectAll(new Member(), null);
		
		if(phone==null){//admin
			
			for(Member root : memberPool){
				if(root.getPid()==0){//root member
					parentMembers.add(root);
					
					Node rootNode = new Node();
					rootNode.setId(root.getId());
					rootNode.setText(buildNodeText(root));
					nodeMap.put(root.getId(), rootNode);
					memberData.add(rootNode);
				}
			}
			
		}else {
			Member root = memberDao.selectByPhone(phone);
			parentMembers.add(root);
			
			Node rootNode = new Node();
			rootNode.setId(root.getId());
			rootNode.setText(buildNodeText(root));
			nodeMap.put(root.getId(), rootNode);
			memberData.add(rootNode);
		}
		
		
		findChildrenNode(parentMembers,memberPool,nodeMap);
		
		
		return memberData;
	}
	
	
	


	private void findChildrenNode(List<Member> parentMembers, List<Member> memberPool, Map<Integer, Node> nodeMap){
		
		
		
		if(parentMembers.isEmpty()){
			return;
		}
		
		List<Member> childrenMembers = new ArrayList<Member>();
		
		for(Member parent : parentMembers){
			
			Node parentNode =  nodeMap.get(parent.getId());
			
			for(Member child : memberPool){
				
				if(parent.getId()==child.getPid()){
					
					childrenMembers.add(child);
					
					Node childNode = new Node();
					childNode.setId(child.getId());
					childNode.setText(buildNodeText(child));
					
					nodeMap.put(child.getId(), childNode);
					parentNode.addNode(childNode);
				}
			}
		}
		
		findChildrenNode(childrenMembers,memberPool,nodeMap);
		
	}
	
	
	private String buildNodeText(Member member){
		StringBuffer text = new StringBuffer();
		
		text.append(member.getName());
		text.append(" ");
		text.append(bulidLevelName(member));
		text.append(" (");
		text.append(member.getOrderCount());
		text.append("，");
		text.append(member.getTotalAmount());
		text.append(")");
		return text.toString();
	}



	private String bulidLevelName(Member member) {
		int level = member.getLevel();
		String levelName = "";
		if(level==1){
			levelName="会员";
		}else if(level==2){
			levelName="高级会员";
		}else if(level==3){
			levelName="黄金会员";
		}else if(level==4){
			levelName="白金会员";
		}else if(level==5){
			levelName="渠道商";
		}else if(level==6){
			levelName="高级代理商";
		}else if(level==7){
			levelName="高级合伙人";
		}
		return levelName;
	}



	@Override
	public boolean isPhoneExist(String phone) {
		Member dbmember = memberDao.selectByPhone(phone);
		return dbmember!=null;
	}



	@Override
	public List<Member> selectChildMembers(Member query, Page<Member> page, String phone) {
		if(phone==null){//admin
			return memberDao.selectAll(query, page);
		}else {
			
			List<Member> childMembers = buildChildMember(phone);
			
			
			Collections.sort(childMembers);
			
			
			
			page.setTotalRecord(childMembers.size());
			if(page.getPageNo() < 1 ){
				page.setPageNo(1);
			}
			if(page.getPageNo() > page.getTotalPage()){
				page.setPageNo(page.getTotalPage());
			}
			
			
			int start = (page.getPageNo()-1)*page.getPageSize();
			
			int end = (page.getPageNo()*page.getPageSize()) > childMembers.size() ? childMembers.size() : page.getPageNo()*page.getPageSize();
			
			return childMembers.subList(start, end);
		}
		
	}



	public List<Member> buildChildMember(String phone) {
		
		List<Member> result = new ArrayList<Member>();
		List<Member> parentMembers = new ArrayList<Member>();
		List<Member> memberPool = memberDao.selectAll(new Member(), null);
		Member root = memberDao.selectByPhone(phone);
		
		parentMembers.add(root);
		result.add(root);
		
		findChildren(parentMembers,memberPool,result);
		
		return result;
	}
	
	
	private void findChildren(List<Member> parentMembers, List<Member> memberPool, List<Member> result){
		
		
		
		if(parentMembers.isEmpty()){
			return;
		}
		
		List<Member> childrenMembers = new ArrayList<Member>();
		
		for(Member parent : parentMembers){
			
			for(Member child : memberPool){
				try {
					if(parent.getId()==child.getPid()){
						
						childrenMembers.add(child);
						
						result.add(child);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		}
		
		findChildren(childrenMembers,memberPool,result);
		
	}



	@Override
	public String login(HttpServletRequest request, String phone, String name) {
		Member loginMember = memberDao.selectByPhone(phone);
		
		
		if(loginMember != null){
			
			if(loginMember.getName().equals(name)
					){
				HttpSession session = request.getSession();
				session.setAttribute(Constant.LOGIN_MEMBER, loginMember);
				return "success";
			}else {
				return "incorrect";
			}
			
		}
		return "incorrect";
	}



	@Override
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.LOGIN_MEMBER);
		session.invalidate();
	}


	
}



