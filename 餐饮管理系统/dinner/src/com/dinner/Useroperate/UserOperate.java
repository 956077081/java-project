package com.dinner.Useroperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.dinner.Until.DBUtil;
import com.dinner.User.User;
import com.dinner.baseSql.baseSql;

public class UserOperate extends baseSql  {
	public User checkLogin(User u) throws Exception
	{
		String sql = " select id,username,password,type,exist "
					+ "from user "
					+ "where username = ? and  password = ? and type = ? and exist = 1";
		PreparedStatement pst=null;
		ResultSet rs=null;
		User at = null;
		/*baseSql sql2 = new baseSql();
		 Connection connection = sql2.getConnection();*/
		
		Connection conn = DBUtil.getConnection();
	
		try {
			pst  =  conn.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setInt(3, u.getType());
			rs=pst.executeQuery();
			String sqlString = pst.toString();//com.mysql.jdbc.JDBC4PreparedStatement@511a6e:  select id,username,password,
			System.out.println("\r\n"+sqlString+"\r\n");
			if(rs.next()){
				at = new User();
				at.setId(rs.getInt(1));
				at.setUsername(rs.getString(2));
				at.setPassword(rs.getString(3));
				at.setType(rs.getInt(4));
				at.setExist(rs.getInt(5));
				
			}
		} catch (Exception e) {
			System.out.println("execteError");
		}finally{
			DBUtil.closeConn(conn);
		}
		return at;
		
	}
}
