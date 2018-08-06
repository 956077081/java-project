package com.dinner.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinner.User.Count;
import com.dinner.User.Page;
import com.dinner.Useroperate.FormOpterate;
import com.dinner.comm.CommFunc;

public class censusServlet extends HttpServlet {



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		FormOpterate  formop  = new FormOpterate();

		//初始化
		int pageNum  = 1;
		int pageSize = 8;
		int cid = 0;  //菜品id
		int type = -1;//菜品状态  默认-1 全选 
		if(CommFunc.isNotEmpty( request.getParameter("pageNum") ) ){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(CommFunc.isNotEmpty(request.getParameter("pageSize")) ){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		if(CommFunc.isNotEmpty(request.getParameter("cid")) ){
			cid  =  Integer.parseInt(request.getParameter("cid"));
		}
	
		if(!CommFunc.isNotEmpty(action)){	
			List<Count> flist = formop.findAll(pageNum);
			double sum = 0;
			for(Count cc :flist){
				sum += cc.getFincome();
			}
			Page<Count> page = new Page<Count>();
			page.setData(flist);
			page.setPageNum(pageNum);
			page.setPageSize(pageSize);
			double monthprice  =formop.getMonthPrice();
			double dayprice  =formop.getdaypricePrice();
			page.setTotal(flist.get(1).getCounts());
			request.setAttribute("page",page);
			request.setAttribute("sum", sum);
			request.setAttribute("monthprice", monthprice);
			request.setAttribute("dayprice", dayprice);
			request.getRequestDispatcher("fmanage/form.jsp").forward(request, response);
			
		}
	
	}

	

}
