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
			  //开始桌面的显示页面
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
			  //添加桌子
			  response.sendRedirect("dmanage/deskAdd.jsp");
		  }else if("addover".equals(action)){
			  dname = request.getParameter("dname").trim();
			  //查重
			  boolean cheakname = deskops.cheakName(dname);
			  if(!cheakname){ //无重复时  添加
				  boolean  insertdes = deskops.insertDesk(dname);
				  if(insertdes){
					  response.sendRedirect("dmanage/success.jsp");
				  }
			  }else{
				  response.sendRedirect("dmanage/error.jsp");
			  }
			  
		  }else if("update".equals(action)){
			  	 //修改餐桌名称
			  	//查找该did
			    Desk desk =  deskops.getDesk(did);
			    request.setAttribute("desk", desk);
			    request.getRequestDispatcher("dmanage/deskUpdate.jsp").forward(request, response);
		  }else if("updateover".equals(action)){
			  //更新桌子名称
			  System.out.println(did);
			  System.out.println();
			  dname =  request.getParameter("dname");
			  boolean cheakname = deskops.cheakName(dname);//不能重名
			  if(!cheakname){
				  //更新名称
				   boolean booupdate = deskops.updateName(did, dname);
				   if(booupdate){
					  response.sendRedirect("dmanage/success.jsp");
				   }
			  }
			 
 
		  }else if("use".equals(action)){
			  //开启餐桌  设置状态为 d_exist1
			  //param did  
			  boolean boosetex =  deskops.setExist(did, 1);
			  if(boosetex){
				  response.sendRedirect("dmanage/success.jsp");
			  }else{
				  response.sendRedirect("dmanage/error.jsp");
			  }
		  }else if("ban".equals(action)){
			  //关闭餐桌  设置状态为  d_exist 0
			  boolean boosetex =  deskops.setExist(did, 0);
			  if(boosetex){
				  response.sendRedirect("dmanage/success.jsp");
			  }else{
				  response.sendRedirect("dmanage/error.jsp");
			  }
		  }
	}

}
