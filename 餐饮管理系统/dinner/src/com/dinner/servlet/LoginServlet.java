package com.dinner.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.UserDataHandler;

import com.dinner.User.User;
import com.dinner.Useroperate.UserOperate;

public class LoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String action        = request.getParameter("action");
		
		HttpSession session  = request.getSession();
		UserOperate ueOperate = new UserOperate();
		  if("login".equals(action)){
		   
		    	String aname=request.getParameter("username");
		    	String apwd=request.getParameter("password");
		    	int type=Integer.parseInt(request.getParameter("type"));
		    
		    	User u  =new User();
		    	u.setUsername(aname);
		    	u.setPassword(apwd);
		    	u.setType(type);
		    	User user=null;
		    	
				try {
					user = ueOperate.checkLogin(u);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("error");
				}
			
				if (user != null ){
					//session 传参  跳转到其他jsp页面  user为  账号的登录信息
					session.setAttribute("user", user);
					//设置生命周期 
					session.setMaxInactiveInterval(60*60*60*24);//一天
					response.sendRedirect("menu");  //跳转的页面或servlet
				}else{
					//登录失败  跳转回 login.jsp
				//	session.setAttribute("name", "账户或密码错误!");
					//session.setMaxInactiveInterval(60*60*24);//一天
					request.setAttribute("bugs", "账户或密码错误!");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
					
				}
		      
		  }else if( "exit".equals(action)){
			  //删除session
			  session.removeAttribute("user");
			  response.sendRedirect("Login.jsp");
		  }
	}


}
