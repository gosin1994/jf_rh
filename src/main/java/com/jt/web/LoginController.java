package com.jt.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.common.Constant;
import com.jt.common.util.SystemUtil;
import com.jt.entity.User;
import com.jt.service.UserService;



@Controller
@RequestMapping("/")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginIndex(HttpServletRequest request){
		return "/login";
	}
	
	@RequestMapping(value="getCode", method=RequestMethod.GET)
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 在内存中创建图象
		int width = 60, height = 25;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandColor(1, 6));
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 24));

		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(1, 10));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String randCode = null;
			// String rand = String.valueOf(random.nextInt(2));
			// if (rand.equals("0")) {
			randCode = String.valueOf(random.nextInt(10));
			// } else {
			// randCode = String.valueOf((char) (random.nextInt(26) + 65));
			// }
			sRand += randCode;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(randCode, 13 * i + 6, 20);
		}

		//将当前验证码存入到session中  
		request.getSession().setAttribute(Constant.KAPTCHA_SESSION_KEY, sRand);
		
		// 图象生效
		g.dispose();

		try {
			// 输出图象到页面
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			logger.error("获取验证码异常!", e);
		}
	}
	
	
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		// int b = fc + random.nextInt(bc - fc);
		return new Color(255 - r, 255 - g, 250);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, String phone,String password,String verifyCode){
		try{
			if(!verifyCode.equalsIgnoreCase((String)request.getSession().getAttribute(Constant.KAPTCHA_SESSION_KEY))){
				return "redirect:/login?msg=verify";
			}
			String msg = userService.login(request, phone, password);
			if("success".equals(msg)){
				
				return "redirect:/i/apply";
			}else {
				return "redirect:/login?msg="+msg;
			}
		}catch(Exception e){
			logger.error("User {} login error!",phone,e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String doLogin(HttpServletRequest request){
		userService.logout(request);
		return "redirect:/login";
	}
	
}
