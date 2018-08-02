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
import com.dinner.User.User;
import com.dinner.Useroperate.DeskOperate;
import com.dinner.comm.CommFunc;

public class menu extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		if (user != null ){
			
			//��Ϊnullʱ   ��ת���˵�ҳ��  
			//�����ҳ
			int type     = 1 ;//Ĭ�ϵ�����Ϊȫ��
			int pageNum  = 1 ;//��ǰҳ��
			int pageSize = 8 ;//��ʾ�����Ӹ���
			if(CommFunc.isNotEmpty( request.getParameter("pageNum") )) {
				pageNum = Integer.parseInt( request.getParameter("pageNum") );
			}
			if(CommFunc.isNotEmpty( request.getParameter("pageSize") )) {
				pageSize = Integer.parseInt( request.getParameter("pageSize") );
			}
			if(CommFunc.isNotEmpty(  request.getParameter("type") )) {
				type= Integer.parseInt(request.getParameter("type"));
			}
			
			//��ѯ����
			DeskOperate deskop  = new DeskOperate();
			//�������Ӽ���
			List<Desk> deskList =  deskop.listDesk(pageNum, pageSize, type);
	
			//ǰ̨��ҳ��
			Page<Desk> page =  new Page<Desk>();
			page.setData(deskList);
			page.setPageNum(pageNum);
			page.setPageSize(pageSize);
			page.setTotal(deskop.getDeskCount(type));
			request.setAttribute("type", type);
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			//��¼����
			request.setAttribute("bug", "��¼����!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
