package com.dinner.Useroperate;


import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dinner.Until.DBUtil;
import com.dinner.User.Desk;


public class DeskOperate {
	Connection conn = null;
	ResultSet res   = null;
	PreparedStatement pre = null;
	//构造
	public DeskOperate(Connection conn, ResultSet res, PreparedStatement pre) {
		super();
		this.conn = conn;
		this.res = res;
		this.pre = pre;
	}
	public DeskOperate() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 返回参数数目
	 * @param type  餐桌占用情况
	 * @return
	 */
	public int getDeskCount(int type )
	{
		//1  代表全部
		//2 代表 占用
		//3 代表空
		int count = 0  ;
		String sql  = "select count(*) from desk where d_exist = '1'";
		if(type == 3){ //代表 有人  状态为 0 
			sql  += "and d_status = 0";
		}
		if(type == 2){
			sql += "and d_status <> 0";
			
		}
		//执行sql
		
		 try {
			 conn =  DBUtil.getConnection();
			
			 pre  =  conn.prepareStatement(sql);
			 res  =  pre.executeQuery();
			 while(res.next()){
				 count = res.getInt(1);
			 }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return count;
	}
	/**
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param type
	 * @return
	 */
	
	public List<Desk> listDesk(int pageNum ,int pageSize ,int type) {
			List<Desk>  list  = new ArrayList<Desk>();
		
			 conn =  DBUtil.getConnection();
			String sql = " select * from desk"; 
			if(type == 3){
				sql +=  " where  d_status = 0 ";// 0代表空
			}
			if( type == 2){
				sql +=  " where  d_status <> 0 ";  //1代表桌子占用
			}
			sql += " order by  d_name  limit ? , ?";
			try {
				pre =  conn.prepareStatement(sql);
				
				int start = (pageNum - 1)*pageSize ;//从id=0开始
				int end   = pageNum*pageSize;
				pre.setInt(1, start);
				pre.setInt(2, end-start);
				System.out.println(pre.toString());
				res = pre.executeQuery();
				while(res.next()){
					Desk desk = new Desk();
					desk.setDid( res.getInt(1) );
					desk.setDname(res.getString(2) );
					desk.setDstatus( res.getInt(3) );
					desk.setDexist(res.getInt(4));
					list.add(desk);
				}
				
			} catch (Exception e) {
				System.out.println("pageerror");
				e.printStackTrace();
		
			}finally{
				DBUtil.closeConn(conn);
			}
			
		
			return list;
	}
	/**
	 * 
	 * @param dname
	 * @return
	 */
	public boolean cheakName(String dname){
		conn = 	DBUtil.getConnection();
		int count = 0;
		String sql = " select count(*) from desk where d_name  = ? ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, dname);
			res = pre.executeQuery();
			if(res.next()){
				count = res.getInt(1);
			}
		} catch (Exception e) {
				System.out.println("checknamedeskerror");
		}
		return count >0 ?true:false;
	}
	/**
	 * 
	 * @param dname
	 * @return
	 */
	public boolean insertDesk(String dname){
		conn = 	DBUtil.getConnection();
		int count = 0;
		String sql = " insert into desk(d_name,d_status,d_exist) values (?,?,?) ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, dname);
			pre.setInt(2, 0);
			pre.setString(3, "1");
			count = pre.executeUpdate();
		
		} catch (Exception e) {
				System.out.println("insertnamedeskerror");
		}
		return count >0 ?true:false;
	}
	/**
	 * 
	 * @param did
	 * @return
	 */
	public   Desk  getDesk( int did ){
		conn = 	DBUtil.getConnection();
		Desk deks  = new Desk();
		
		String sql = " select * from desk where d_id = ? ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, did);
			res = pre.executeQuery();
			if(res.next()){
			
				deks.setDid(res.getInt(1));
				deks.setDname(res.getString(2));
				deks.setDstatus(res.getInt(3));
				deks.setDexist(res.getInt(4));
			}
		
		} catch (Exception e) {
				System.out.println("insertnamedeskerror");
		}
		return deks;
	}
	/**
	 * 
	 * @param did
	 * @param dname
	 * @return
	 */
	public boolean updateName(int did ,String dname)
	{
		conn = 	DBUtil.getConnection();
		int count = 0;
		String sql = " update desk set d_name  = ? where d_id  = ? ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, dname);
			pre.setInt(2, did);
			System.out.println(pre.toString());
			count = pre.executeUpdate();
		
		} catch (Exception e) {
				System.out.println("insertnamedeskerror");
		}
		return count >0 ?true:false;
	}
	public  boolean setExist(int did, int exist){
			conn   = DBUtil.getConnection();
			int count = 0;
			String sql = " update desk set d_exist = ? where  d_id = ? ";
			try {
				pre = conn.prepareStatement(sql);
				if(exist  == 0){
					pre.setString(1, "0");
				}else{
					pre.setString(1, "1");
				}
				pre.setInt(2, did);
				System.out.println(pre.toString());
				count = pre.executeUpdate();
			} catch (Exception e) {
				System.out.println("error");
			}
			return count>0 ? true:false;
	} 
	public  boolean setState(int did, int status){
		conn   = DBUtil.getConnection();
		int count = 0;
		String sql = " update desk set d_status = ? where  d_id = ? ";
		try {
			pre = conn.prepareStatement(sql);
			
				pre.setInt(1, status);
			
			pre.setInt(2, did);
			System.out.println(pre.toString());
			count = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return count>0 ? true:false;
} 
	

}
