package com.jt.common.util;

import java.io.File;
import java.util.Locale;

import org.springframework.web.servlet.view.InternalResourceView;

/**
 * @date 2018-9-6
 * @author gosin
 *
 */
public class HtmlResourceView extends InternalResourceView {
	@Override
	public boolean checkResource(Locale locale) throws Exception {
		File file = new File(this.getServletContext().getRealPath("/") + getUrl());  
	     return file.exists();// 判断该页面是否存在 
	}

}
