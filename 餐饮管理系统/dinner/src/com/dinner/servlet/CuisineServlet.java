package com.dinner.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinner.User.Cuisine;
import com.dinner.User.Menutype;
import com.dinner.User.Page;
import com.dinner.Useroperate.CuisineOperate;
import com.dinner.Useroperate.MenutypeOpterate;
import com.dinner.comm.CommFunc;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

public class CuisineServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
    
		CuisineOperate cuisineop  = new CuisineOperate();
		//��ʼ��
		int pageNum  = 1;
		int pageSize = 8;
		int cid = 0;  //��Ʒid
		int type = -1;//��Ʒ״̬  Ĭ��-1 ȫѡ 
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
			List<Cuisine> clist = cuisineop.findAllByPage(pageNum, pageSize);
			Page<Cuisine> page = new Page<Cuisine>();
			page.setData(clist);
			page.setPageNum(pageNum);
			page.setPageSize(pageSize);
			page.setTotal(cuisineop.getCount());
			request.setAttribute("page",page);
			request.getRequestDispatcher("cmanage/cuisine.jsp").forward(request, response);
		}
		 if ("xiajia".equals(action) ){
			//���ò�Ʒ��״̬Ϊ 0 
			boolean bool  =cuisineop.setExist(0, cid);
			if(bool){
				response.sendRedirect("cmanage/success.jsp");
			}
		}else if ("shangjia".equals(action)) {
			
			boolean bool  =cuisineop.setExist(1, cid);
			if(bool){
				response.sendRedirect("cmanage/success.jsp");
			}
		}else if("add".equals(action)){
			//�õ���Ʒ����
			
			MenutypeOpterate  menuoper = new MenutypeOpterate();
			List<Menutype> listmenu = menuoper.ListMenutype(pageNum, pageSize, 1);
			request.setAttribute( "listmenu" , listmenu );
			request.getRequestDispatcher("cmanage/addcuisine.jsp").forward(request,response);
		}else if ("addover".equals(action)) {
			//�洢 ��Ʒ
			request.setCharacterEncoding("utf-8");
			String cname  = new String(request.getParameter("cname").getBytes("ISO-8859-1"),"utf-8");
			String cinfo  = new String(request.getParameter("cinfo").getBytes("ISO-8859-1"),"utf-8");
			String cimg  = request.getParameter("cimg");
			double ccost = 0f;
			double cprice = 0f;
			int cmid = 0;
			
			if(CommFunc.isNotEmpty(request.getParameter("ccost")) ){
				ccost  =  Double.parseDouble(request.getParameter("ccost"));
			}
		
			if(CommFunc.isNotEmpty(request.getParameter("cprice")) ){
				cprice  =  Double.parseDouble(request.getParameter("cprice"));
			}
			if(CommFunc.isNotEmpty(request.getParameter("mid")) ){
				cmid  =  Integer.parseInt(request.getParameter("mid"));
			}
			//����Ƿ�������
			boolean samename =  cuisineop.checkName(cname);
			if(samename){
				//���ִ���
				response.sendRedirect("cmanage/success.jsp");
			}
			
			//����ͼƬ
			SmartUpload  upload = new SmartUpload();
		
			upload.initialize(this.getServletConfig(),request,response);
			try {
				upload.upload();
	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("����ʧ��");
			}
			Files files = upload.getFiles();
			 String newfilename = "";
			for(int i = 0 ;i<files.getCount(); i++){
				File file = files.getFile(i);
				String path = this.getServletContext().getRealPath("images");
		
				String filename =  cimg;
				
				String filetype =  filename.substring(filename.lastIndexOf("."));
				System.out.println(filetype);
			    long nowDate = new Date().getTime();
		        String sid = Integer.toHexString((int)nowDate);
		        newfilename = sid+filetype;
	
				path = path+"\\"+newfilename;
				System.out.println(path);
				try {
					file.saveAs(path);
				} catch (Exception e) {
					System.out.println("�ϴ�����!");
				}
			}
			
			boolean bool =  cuisineop.insertCuisine(cname, ccost, cprice,  newfilename, cmid,cinfo);
			if(bool){
				response.sendRedirect("cmanage/success.jsp");
			}else{
				
			}
			
		}else if("update".equals(action)){
			System.out.println(cid);
			//���� cid
			Cuisine cuicid = cuisineop.checkCid(cid);
			//�������еĲ�Ʒ����
			MenutypeOpterate  menuoper = new MenutypeOpterate();
			List<Menutype> listmenu = menuoper.ListMenutype(pageNum, pageSize, 1);
			request.setAttribute("cu", cuicid);
			request.setAttribute("mlist", listmenu);
			request.getRequestDispatcher("cmanage/updatecuisine.jsp").forward(request, response);
		}else if("updateover".equals(action)){
			//�ò�Ʒ������
			int mid = 0;
			
			if(CommFunc.isNotEmpty(request.getParameter("type")) ){
				mid  =  Integer.parseInt(request.getParameter("type"));
			}
			double  pricess= 0f;
			if(CommFunc.isNotEmpty(request.getParameter("cprice")) ){
				pricess  =  Double.parseDouble(request.getParameter("cprice"));
			}
			String infoString = request.getParameter("cinfo");
			//���±�
			boolean boo4upda =  cuisineop.updateCuisine(cid,mid,pricess,infoString);
			if(boo4upda){
				response.sendRedirect("cmanage/success.jsp");
			}
		}
	}
 }



