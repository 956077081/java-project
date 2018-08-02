package com.dinner.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinner.User.Desk;
import com.dinner.User.Page;
import com.dinner.Useroperate.DeskOperate;
import com.dinner.comm.CommFunc;

public class DeskServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action  =  request.getParameter("action");
		int did = 0 ;
		int pageNum =1;
		int pageSize = 8;
		int type = 1;
		DeskOperate deskops = new DeskOperate();
		String dname = null;
		if(CommFunc.isNotEmpty(request.getParameter("did"))){
			did = Integer.parseInt(request.getParameter("did"));
		} 
		if(CommFunc.isNotEmpty(request.getParameter("pageNum"))){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} 
		 
		  //dname
		  if(!CommFunc.isNotEmpty(action)){
			  //��ʼ�������ʾҳ��
			  List<Desk> listdesk = deskops.listDesk(pageNum, pageSize, type);
			  Page<Desk> page = new Page<Desk>();
			  page.setData(listdesk);
			  page.setPageNum(pageNum);
			  page.setPageSize(pageSize);
			  page.setTotal(deskops.getDeskCount(type));
			  request.setAttribute("page", page);
			  request.getRequestDispatcher("dmanage/deskM.jsp").forward(request, response);
		  }
		  if("add".equals(action)){
			  //�������
			  response.sendRedirect("dmanage/deskAdd.jsp");
		  }else if("addover".equals(action)){
			  dname = request.getParameter("dname").trim();
			  //����
			  boolean cheakname = deskops.cheakName(dname);
			  if(!cheakname){ //���ظ�ʱ  ���
				  boolean  insertdes = deskops.insertDesk(dname);
				  if(insertdes){
					  response.sendRedirect("dmanage/success.jsp");
				  }
			  }else{
				  response.sendRedirect("dmanage/error.jsp");
			  }
			  
		  }else if("update".equals(action)){
			  	 //�޸Ĳ�������
			  	//���Ҹ�did
			    Desk desk =  deskops.getDesk(did);
			    request.setAttribute("desk", desk);
			    request.getRequestDispatcher("dmanage/deskUpdate.jsp").forward(request, response);
		  }else if("updateover".equals(action)){
			  //������������
			  System.out.println(did);
			  System.out.println();
			  dname =  request.getParameter("dname");
			  boolean cheakname = deskops.cheakName(dname);//��������
			  if(!cheakname){
				  //��������
				   boolean booupdate = deskops.updateName(did, dname);
				   if(booupdate){
					  response.sendRedirect("dmanage/success.jsp");
				   }
			  }
			 
 
		  }else if("use".equals(action)){
			  //��������  ����״̬Ϊ d_exist1
			  //param did  
			  boolean boosetex =  deskops.setExist(did, 1);
			  if(boosetex){
				  response.sendRedirect("dmanage/success.jsp");
			  }else{
				  response.sendRedirect("dmanage/error.jsp");
			  }
		  }else if("ban".equals(action)){
			  //�رղ���  ����״̬Ϊ  d_exist 0
			  boolean boosetex =  deskops.setExist(did, 0);
			  if(boosetex){
				  response.sendRedirect("dmanage/success.jsp");
			  }else{
				  response.sendRedirect("dmanage/error.jsp");
			  }
		  }
	}

}
