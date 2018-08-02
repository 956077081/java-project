package com.dinner.Useroperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dinner.Until.DBUtil;


public class FormOpterate {
	Connection conn = null;
	PreparedStatement pre = null;
	ResultSet res  = null;
	int count = 0;
	/**
	 * 
	 * @param did
	 * @return
	 */
	public boolean checkFid(int did){
		conn = DBUtil.getConnection();
		String sql = " select count(*) from order_form where d_id = ?  and f_state = '0' " ;
		int result = 0 ;
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, did);
			System.out.println(pre.toString());
			res  =  pre.executeQuery();
	
			if(res.next()){
				result = res.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeConn(conn);
		}
		System.out.println("resulrchecjk="+result);
		return result > 0 ?true :false; 
	}
	public boolean insertForm(int did,int aid)
	{
		
		conn = DBUtil.getConnection();
		String sql = "   insert into order_form(d_id,f_date,f_state,a_id) values (?,sysdate() ,'0',?) " ;

		try {
			System.out.println("error");
			pre = conn.prepareStatement(sql);
			pre.setInt(1, did);
			pre.setInt(2, aid);
			System.out.println(pre.toString());
			count  =  pre.executeUpdate();
			System.out.println(count+"asdsadas");
			
		} catch (Exception e) {
			System.out.println("inseterror");
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn);
		}
		return count > 0 ?true :false; 
	}
	/**
	 * 
	 * @param did
	 * @return
	 */
	public int getFid(int did){
		conn = DBUtil.getConnection();
		String sql = " select f_id from order_form where d_id = ? and  f_state= '0'" ;
		int result = 0 ;
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, did);
			System.out.println(pre.toString());
			res  =  pre.executeQuery();
			if(res.next()){
				result = res.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getFid= errro");
		}finally{
			DBUtil.closeConn(conn);
		}
		System.out.println("result="+result);
		return result;
	}
	public boolean deleteForm(int fid ){

		conn = DBUtil.getConnection();
		String sql = "   delete  from order_form where f_id = ? " ;

		try {
			System.out.println("error");
			pre = conn.prepareStatement(sql);
			pre.setInt(1, fid);
			
			System.out.println(pre.toString());
			count  =  pre.executeUpdate();
		
			
		} catch (Exception e) {
			System.out.println("inseterror");
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn);
		}
		return count > 0 ?true :false; 
	}
}
