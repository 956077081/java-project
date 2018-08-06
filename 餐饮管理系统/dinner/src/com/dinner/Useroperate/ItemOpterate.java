package com.dinner.Useroperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.dinner.Until.DBUtil;
import com.dinner.User.Cuisine;
import com.dinner.User.Item;


public class ItemOpterate {
	Connection conn  = null;
	PreparedStatement pre = null;
	ResultSet  rs =  null;
	int count = 0;
	public Map<Integer,Item >   showItem( int fid)
	{
		Map<Integer,Item> map = new HashMap<Integer, Item>();
		conn = DBUtil.getConnection();
		String sql  = " select c.c_id,c.c_name,c.c_price,i.i_num "
				+     "from  order_form f,Cuisine c,order_item i  "
				+ "    where f.f_id=i.f_id and i.c_id=c.c_id and i.f_id= ? ";
		try {
			pre =  conn.prepareStatement(sql);
			pre.setInt(1, fid);
			System.out.println(pre.toString());
			rs = pre.executeQuery();
		
			while(rs.next()){
				Cuisine cuisine = new Cuisine();
				Item  item = new Item();
				cuisine.setCid(rs.getInt(1));
				cuisine.setCname(rs.getString(2));
				cuisine.setCprice(rs.getDouble(3));
				item.setCuisine(cuisine);
				item.setFid(fid);
				item.setInum(rs.getInt(4));
				item.setSum(rs.getDouble(3)*rs.getInt(4));
				//map 存储
				map.put(rs.getInt(1), item);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeConn(conn);
		}
		return map;
		
	}
	/**
	 * 
	 * @param fid
	 * @param cid
	 * @param num
	 * @return
	 */
	public boolean updateNum(int fid  , int cid ,int num)
	{
		conn = DBUtil.getConnection();
		String sql = " update order_item set i_num= ? where f_id = ? and c_id = ? ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, num);
			pre.setInt(2, fid);
			pre.setInt(3, cid);
			System.out.println(pre.toString());
		   count = pre.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("upitemError");
		}finally{
			DBUtil.closeConn(conn);
		}
		return count > 0? true:false;
	}
	public boolean deleteItem(int fid  , int cid){
		conn = DBUtil.getConnection();
		String sql = " delete from order_item where f_id = ? and c_id = ?  ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, fid);
			pre.setInt(2, cid);
			System.out.println(pre.toString());
		   count = pre.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("deleteitemError");
		}finally{
			DBUtil.closeConn(conn);
		}
		return count > 0? true:false;
	}
	public boolean insertItem(int fid ,int cid){
		//检查 是否含有该项
		conn  =DBUtil.getConnection();
		int result1  =0;
		
		String sqlone =  " select count(*) from order_item where f_id =? and c_id =?";
		try {
			pre = conn.prepareStatement(sqlone);
			pre.setInt(1, fid);
			pre.setInt(2, cid);
			rs  = pre.executeQuery();
			System.out.println(rs.toString());
			if(rs.next()){
				result1 = rs.getInt(1);
			}
		} catch (Exception e) {
		System.err.println("error_depe1");
		}
		if(result1>0){
			String sqltwo =  " update order_item set i_num=i_num+1  where f_id =? and c_id =?";
			try {
				pre = conn.prepareStatement(sqltwo);
				pre.setInt(1, fid);
				pre.setInt(2, cid);
				System.out.println(sqltwo);
				count = pre.executeUpdate();
				
			} catch (Exception e) {
			System.err.println("error_depe2");
			}
		}else{
			String sqltwo =  " insert into order_item values  (? ,?,1)";
			try {
				pre = conn.prepareStatement(sqltwo);
				pre.setInt(1, fid);
				pre.setInt(2, cid);
				count = pre.executeUpdate();
				System.out.println(sqltwo);
			} catch (Exception e) {
			System.out.println("error_depe2");
			}
		}
		DBUtil.closeConn(conn);
		return count>0 ?true:false;
	}
	
	public boolean deleteItemByfid(int fid){
		conn = DBUtil.getConnection();
		String sql = " delete from order_item where f_id = ?  ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, fid);
		
			System.out.println(pre.toString());
		   count = pre.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("deleteitemError");
		}finally{
			DBUtil.closeConn(conn);
		}
		return count > 0? true:false;
	}
	
}
