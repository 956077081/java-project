package com.dinner.Useroperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dinner.Until.DBUtil;
import com.dinner.User.Count;
import com.dinner.User.Desk;


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
	
	public double getdaypricePrice(){
		conn  =  DBUtil.getConnection();
		Double sum =  0.0;
		String  sql   = "select sum(f_income) as sum from order_form where date_format(f_date2, '%Y %m %d')  = date_format(CURRENT_DATE, '%Y %m %d') and f_state ='1' ";
		try {
			pre = conn.prepareStatement(sql);
		
			res  =  pre.executeQuery();
	
			if(res.next()){
				sum = res.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeConn(conn);
		}
		return sum;
	}
	public double getMonthPrice(){
		conn  =  DBUtil.getConnection();
		Double sum =  0.0;
		String  sql   = "select sum(f_income) as sum from order_form where date_format(f_date2, '%Y %m')  = date_format(CURRENT_DATE, '%Y %m') and f_state = '1' ";
		try {
			pre = conn.prepareStatement(sql);
		
			res  =  pre.executeQuery();
	
			if(res.next()){
				sum = res.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeConn(conn);
		}
		return sum;
	}
	/**
	 * 
	 * @param did
	 * @return
	 */
	public List<Count> findAll( int pageNum){
		System.out.println(pageNum+"pagenum");
		conn = DBUtil.getConnection();
		List<Count>  list  = new ArrayList<Count>();
		String sql = " SELECT i.f_id ,u.username  , f.f_income ,f.f_date2 ,GROUP_CONCAT(c_name) as cuisines ,count(*)"
				+ "  FROM order_item i ,cuisine  c , order_form f ,user u "
				+ " where f.f_state = '1' and f.a_id = u.id and i.c_id = c.c_id and f.f_id  = i.f_id "
				+ " GROUP BY i.f_id  order by f.f_date2 Desc limit ?,8 " ;

		try {
			pre = conn.prepareStatement(sql);
			
		
			int start  =  (pageNum -1)*8;
			
			pre.setInt(1,start);
			res  =  pre.executeQuery();
			System.out.println(pre.toString());
			while(res.next()){
			Count c = new Count();
			c.setFid(res.getInt(1));
			System.out.println(res.getString(2));
			c.setUsername(res.getString(2));
			c.setFincome(res.getDouble(3));
			c.setFdate2(res.getTimestamp(4));
			c.setCuisine(res.getString(5));
			c.setCounts(res.getInt(6));
			list.add(c);
			}
		} catch (Exception e) {
			System.out.println("cherror");
		}finally{
			DBUtil.closeConn(conn);
		}
	
		for ( Count c1 :list) {
			System.out.println(c1.getFincome());
		}
		return list;
	}
	/**
	 * 
	 * @param did
	 * @param aid
	 * @return
	 */
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
