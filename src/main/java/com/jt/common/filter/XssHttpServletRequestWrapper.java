package com.jt.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {  
  
    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);  
    }  
  
    @Override  
    public String getHeader(String name) {  
        return htmlEncode(super.getHeader(name));  
    }  
  
    @Override  
    public String getQueryString() {  
        return htmlEncode(super.getQueryString());  
    }  
  
    @Override  
    public String getParameter(String name) {  
        return htmlEncode(super.getParameter(name));  
    }  
  
    @Override  
    public String[] getParameterValues(String name) {  
        String[] values = super.getParameterValues(name);  
        if(values != null) {  
            int length = values.length;  
            String[] escapseValues = new String[length];  
            for(int i = 0; i < length; i++){  
                escapseValues[i] = htmlEncode(values[i]);  
            }  
            return escapseValues;  
        }  
        return super.getParameterValues(name);  
    }  
    
    
    private static String htmlEncode(char c) {

        switch(c) {

           case '&':

               return "&amp;";

           case '<':

               return "&lt;";

           case '>':

               return "&gt;";

           case '"':

               return "&quot;";

           case ' ':

               return "&nbsp;";

           default:

               return c + "";

        }

    }

     

    /** 对传入的字符串str进行Html encode转换 */

    public static String htmlEncode(String str) {

        if (str ==null || str.trim().equals(""))   return str;

        StringBuilder encodeStrBuilder = new StringBuilder();

        for (int i = 0, len = str.length(); i < len; i++) {

           encodeStrBuilder.append(htmlEncode(str.charAt(i)));

        }

        return encodeStrBuilder.toString();

    }
    
    
    public static void main(String[] args) {
		String s = "<script>window.location.华东href= 'http://www.baidu.com';</script>";
		
		System.out.println(htmlEncode(s));
		System.out.println(StringEscapeUtils.escapeHtml("<a>dddd</a>"));    
		System.out.println(StringEscapeUtils.escapeJavaScript("<script>alert('1111')</script>"));
	}
      
} 