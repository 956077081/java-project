package com.dinner.Until;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


//xml方式数据库连接
public class DBUtil {
	private static String  driver ;
	private static String  URL ;
	private static String  username ;
	private static String  password ;
	//代码赋值 
	static{
		Properties perper = new Properties();
		InputStream  input = DBUtil.class.getClassLoader().getResourceAsStream("com/dinner/Until/jdbc.properties");
		try {
			perper.load(input);
			driver 		= perper.getProperty("driver");
			URL    		= perper.getProperty("url");
			username	= perper.getProperty("username");
			password    = perper.getProperty("password");
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("驱动");
		}
	}
	/**
	 * 连接
	 * @return
	 */
	public static Connection getConnection ()
	{
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, username, password);
		} catch (Exception e) {
			System.out.println("连接");
		}
		return conn;
		
	}
	public static void closeConn(Connection conn) 
	{
		try {
			if(conn!=null && !conn.isClosed() ){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
