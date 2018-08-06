package com.dinner.Useroperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dinner.Until.DBUtil;


public class Orderopterate {
  Connection conn = null;
  PreparedStatement prep = null;
  int count  = 0;
  ResultSet res = null;
  public boolean overForm( int did , int fid , double income){
	  conn =DBUtil.getConnection();
	  String sql =" update order_form set f_date2 = sysdate(), f_state = '1' ,f_income = ?  where d_id = ? and f_id =?";
	  try {
			prep = conn.prepareStatement(sql);
			prep.setDouble(1, income);
			prep.setInt(2, did);
			prep.setInt(3, fid);
			System.out.println(prep.toString());
			count = prep.executeUpdate();
		} catch (Exception e) {
			System.out.println("error");
		}finally{
			DBUtil.closeConn(conn);
		}
		return count>0 ? true:false;
  }
}
