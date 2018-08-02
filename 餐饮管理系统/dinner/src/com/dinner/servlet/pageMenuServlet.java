package com.dinner.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinner.User.Cuisine;
import com.dinner.User.Desk;
import com.dinner.User.MenuPaging;
import com.dinner.User.Menutype;
import com.dinner.User.Page;
import com.dinner.User.User;
import com.dinner.Useroperate.CuisineOperate;
import com.dinner.Useroperate.DeskOperate;
import com.dinner.Useroperate.FormOpterate;
import com.dinner.Useroperate.ItemOpterate;
import com.dinner.Useroperate.MenutypeOpterate;
import com.dinner.comm.CommFunc;

public class pageMenuServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//添加订单
			int did   =0;
			int pageNum = 1;
			int pageSize  =8;
			int cid = 0;
			FormOpterate  formOp = new FormOpterate();
			CuisineOperate cuisineop  = new CuisineOperate();
			DeskOperate 	deskop  = new DeskOperate();
			if(CommFunc.isNotEmpty(request.getParameter("did"))){
				did = Integer.parseInt(request.getParameter("did"));
			}
			if(CommFunc.isNotEmpty(request.getParameter("pageNum"))){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(CommFunc.isNotEmpty(request.getParameter("pageSize"))){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			if(CommFunc.isNotEmpty(request.getParameter("cid"))){
				cid = Integer.parseInt(request.getParameter("cid"));
			}
			double  price1 = 0; //价格下限
			double  price2= 0;  //价格上限
			if(CommFunc.isNotEmpty(request.getParameter("price1"))){
				price1 = Double.parseDouble(request.getParameter("price1"));
			}
			if(CommFunc.isNotEmpty(request.getParameter("price2"))){
				price2 = Double.parseDouble(request.getParameter("price2"));
			}
			String action  = request.getParameter("action");
			if(CommFunc.isNotEmpty(action)){
				int fid = 0;
				if("click".equals(action)){
					//检查   did 在 form表是否存在  不在 创建个 数据
					boolean  boochfid =  formOp.checkFid(did);
					
					if(!boochfid){
						//添加数据
						//获取aid
						System.out.println("insert");
						User u  = (User)request.getSession().getAttribute("user");		
						int aid  = u.getId();
						boolean booinser  =  formOp.insertForm(did ,aid);
						 fid = formOp.getFid(did);
						System.out.println("fid = "+fid);
						boolean boosest =   deskop.setState(did, fid);
						//设置桌子状态
						if(!booinser ||!boosest){
							System.out.println("失败");
						}
						
					}
					//添加菜品
					if(fid == 0){
					   fid = formOp.getFid(did);
					}
					System.out.println("tianjia");
					ItemOpterate  itm = new ItemOpterate();
					boolean  booin  = itm.insertItem(fid,cid);
					if(!booin){
						System.out.println("甜菜失败");
					}
					
				}
			}
			
			String cname = request.getParameter("cname");
			String ctype = request.getParameter("ctype");
			
			MenuPaging mp = new  MenuPaging(price1, price2, cname, ctype);
			List<Cuisine> clist = cuisineop.pageCuisines(pageNum, pageSize ,mp);
		
			Page<Cuisine> page  = new Page<Cuisine>();
			page.setData(clist);
			page.setPageNum(pageNum);
			page.setPageSize(pageSize);
			page.setTotal(cuisineop.getCountbyprice(mp));
			Desk des = deskop.getDesk(did);//did  的桌子
			MenutypeOpterate menuop = new MenutypeOpterate();
			List<Menutype> mlist  =menuop.showAllType();
			request.setAttribute("mplist", mlist);
		    request.setAttribute("mp", mp);
		    request.setAttribute("did", did);
		    request.setAttribute("page", page);
		    request.setAttribute("dstatus", des.getDstatus());
		    request.getRequestDispatcher("dishes.jsp").forward(request, response);			
	}
}
