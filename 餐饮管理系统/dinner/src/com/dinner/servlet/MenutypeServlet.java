package com.dinner.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinner.User.Menutype;
import com.dinner.User.Page;
import com.dinner.Useroperate.CuisineOperate;
import com.dinner.Useroperate.MenutypeOpterate;
import com.dinner.comm.CommFunc;

public class MenutypeServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//目标返回menutype的所有数据
		request.setCharacterEncoding("utf-8");
		MenutypeOpterate menutype  =  new MenutypeOpterate();
		String action =  request.getParameter("action");  //menutype动作
		System.out.println(action+")1111111111111111111");
		int cid =  0; //设置菜品
		int mid =  0; //设置菜类型  id
		int pageNum  = 1;
		int pageSize = 8;
		int type     = -1;
		String mtype = null;
		
		//验证参数
		if(CommFunc.isNotEmpty(request.getParameter("mid")) ){
			mid = Integer.parseInt(request.getParameter("mid")); 
		}
		System.out.println(mid+"+++++++++++");
		if(CommFunc.isNotEmpty(request.getParameter("pageNum")) ){
			pageNum = Integer.parseInt(request.getParameter("pageNum")); 
		}
		if(CommFunc.isNotEmpty(request.getParameter("mtype")) ){
			mtype = request.getParameter("mtype");
		
		}
		if(CommFunc.isNotEmpty(request.getParameter("cid")) ){
			cid  =  Integer.parseInt(request.getParameter("cid"));
		}
		
		if( CommFunc.isNotEmpty(action) != true ){ //为null 时返回所有的实例*
			List<Menutype> listMenutypes = menutype.ListMenutype(pageNum, pageSize, type);
			
			//设置page
			Page<Menutype> page = new Page<Menutype>();
			page.setData(listMenutypes);
			page.setPageNum(pageNum);
			page.setPageSize(pageSize);
			page.setTotal(menutype.getCountMenu());
			//跳转到 
			request.setAttribute("page", page);
			request.getRequestDispatcher("tmanage/mtypeM.jsp").forward(request, response);
		}else if ("add".equals(action)) {
			//修改类型
			response.sendRedirect("tmanage/mtypeAdd.jsp");
		}else if ("addover".equals(action)) {
			System.out.println(mtype);
			boolean bool = menutype.addmenutype(mtype);
			if(bool){
				response.sendRedirect("tmanage/success.jsp");
			}
		}
		else if("ban".equals(action)){
			//设置该状态为0 
			 boolean boo2 = menutype.setExist(0,mid);
			 if(boo2){
					response.sendRedirect("tmanage/success.jsp");
			}
		}else if("use".equals(action)){
			System.out.println(mid+"adasdsasd");
			//设置 该状态为 1
			boolean bool =  menutype.setExist(1, mid);
			if(bool){
				response.sendRedirect("tmanage/success.jsp");
			}else{
				
			}
		}else if("update".equals(action)){
			//修改名字
			String mtypes  = new String(request.getParameter("mtype").getBytes("ISO-8859-1"),"utf-8");
			boolean booupdate =  menutype.updateType(mid,mtypes);
			
			if(booupdate){
				response.sendRedirect("tmanage/success.jsp");
			}
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
