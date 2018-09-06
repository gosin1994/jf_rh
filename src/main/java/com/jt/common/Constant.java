package com.jt.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Constant {
	
	public static final String KAPTCHA_SESSION_KEY = "verifyCode";
	
	public static final String LOGIN_USER = "user";
	
	public static final String LOGIN_MEMBER = "member";
	

	public static final Integer USER_TYPE_ADMIN = 1;
	
	
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	
	
	public static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
	
	public static final Pattern PRICE_PATTERN = Pattern.compile("^\\d+\\.?\\d*$");
	
	
	
	public static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	//local
	//public static final String IMAGE_DIR = "D:/shenchangsheng/workspace/kd-20150306/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/qzgwx/upload/img";
	//D:/static/upload/img
	
	
	//linux
	public static final String IMAGE_DIR = "/root/qzg/static/upload/img";


	
	

	
	
	
}
