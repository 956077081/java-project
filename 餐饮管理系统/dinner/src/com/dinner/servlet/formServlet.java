package com.dinner.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.criteria.Order;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinner.User.Desk;
import com.dinner.User.Form;
import com.dinner.User.Item;
import com.dinner.Useroperate.DeskOperate;
import com.dinner.Useroperate.FormOpterate;
import com.dinner.Useroperate.ItemOpterate;
import com.dinner.Useroperate.Orderopterate;
import com.dinner.comm.CommFunc;

public class formServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		DeskOperate deksOperate = new DeskOperate();
		ItemOpterate  itemOpterate = new ItemOpterate();
		Orderopterate order  =new Orderopterate();
		FormOpterate 	 forop = new FormOpterate();
		String action = request.getParameter("action");
		
		//满座的id
		int did = 0 ;
		if( CommFunc.isNotEmpty(request.getParameter("did"))){
			did  = Integer.parseInt(request.getParameter("did"));
		
		}
	
		//显示桌子的所占 菜单ID
		Desk  de = deksOperate.getDesk(did);
		int fid  =de.getDstatus(); //订单ID
		
		String dname = de.getDname();
		
		if(CommFunc.isNotEmpty(action)){
			int num = 0; //数目
			int cid  = 0;//菜品 的id
			Double incom  = 0.00 ; //是 收
			if( CommFunc.isNotEmpty(request.getParameter("num"))){
				num  = Integer.parseInt(request.getParameter("num"));
			}
			if( CommFunc.isNotEmpty(request.getParameter("cid"))){
				cid  = Integer.parseInt(request.getParameter("cid"));
			}
			if(CommFunc.isNotEmpty(request.getParameter("incom"))){
				incom = Double.parseDouble(request.getParameter("incom"));
			}
			if("update".equals(action)){
				//修改数目
			
				//修改数目  更具item
				boolean booup =  itemOpterate.updateNum(fid, cid, num);
				if(!booup){
					
				}
			}else if ("deleteItem".equals(action)){
				//删除菜单菜
				
				boolean  boole =  itemOpterate.deleteItem(fid, cid);
				if(!boole){
					
				}
			}else if("sellte".equals(action)){
				//释放桌子
				   boolean  boosete =  deksOperate.setState(did, 0);
				   boolean booover = order.overForm(did,fid,incom);
				   if(!boosete  || !booover){
					   
				   }else {
					   response.sendRedirect("menu");
					   return ;
				   }
				   
				//修改 订单状态  和收入
				   	
				   
			}else if("cancel".equals(action)){
				boolean boo1 =  forop.deleteForm(fid);
				boolean  boo2 =  itemOpterate.deleteItemByfid(fid);
				boolean boo3 = deksOperate.setState(did, 0);
				if(boo1==true && boo2 ==true &&boo3 ==true){
					   response.sendRedirect("menu");
					   return ;
				}
				
				
			}
		}
		
		Map<Integer, Item> item = itemOpterate.showItem(fid);
		double sum = 0;
		Iterator<Item> it = item.values().iterator();//迭代器
		while(it.hasNext()){
			//	System.out.println(it.next().getInum());
			sum += it.next().getSum();
		}
	
		Form  form = new Form();
		form.setTotal(sum);
		form.setItems(item);
		form.setFid(fid);
		form.setDeid(did);
		System.out.println(sum);
		request.setAttribute("dname", dname);
		request.setAttribute("orderFrom", form);
		request.getRequestDispatcher("form.jsp").forward(request,response);

	}

}
