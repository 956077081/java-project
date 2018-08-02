package com.dinner.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageFilter implements Filter {
     private String encoder;
 	@Override
 	public void init(FilterConfig arg0) throws ServletException {
 		// TODO Auto-generated method stub
 		encoder  = 	arg0.getInitParameter("encoder");
 	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		    HttpServletResponse resp  =  (HttpServletResponse)arg1;
			HttpServletRequest requ =  (HttpServletRequest)arg0;
			requ.setCharacterEncoding(encoder);
			resp.setContentType("text/html;charset="+encoder);
			arg2.doFilter(requ, resp);
		
		
	}



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
