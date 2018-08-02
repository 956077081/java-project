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
					//session ����  ��ת������jspҳ��  userΪ  �˺ŵĵ�¼��Ϣ
					session.setAttribute("user", user);
					//������������ 
					session.setMaxInactiveInterval(60*60*60*24);//һ��
					response.sendRedirect("menu");  //��ת��ҳ���servlet
				}else{
					//��¼ʧ��  ��ת�� login.jsp
				//	session.setAttribute("name", "�˻����������!");
					//session.setMaxInactiveInterval(60*60*24);//һ��
					request.setAttribute("bugs", "�˻����������!");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
					
				}
		      
		  }else if( "exit".equals(action)){
			  //ɾ��session
			  session.removeAttribute("user");
			  response.sendRedirect("Login.jsp");
		  }
	}


}
