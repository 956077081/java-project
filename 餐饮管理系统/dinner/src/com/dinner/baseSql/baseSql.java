package com.dinner.baseSql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class baseSql
{
	/**
	 * 连接oracle
	 * @return Connection conn
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception
	{//oracle.jdbc.driver.OracleDriver
		//设置包
		Class.forName("com.mysql.jdbc.Driver");
		//"jdbc:oracle:thin:@localhost:1521:XE"
		String URL       =  "jdbc:mysql://localhost:3306/dinner";
		String username  =  "root";
		String password  =  "123456";
		Connection conn  =   DriverManager.getConnection(URL, username, password);
		return conn;

	}
	/**
	 * 查询操作
	 * @param conn
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public ResultSet getQuery(Connection conn ,String sql) throws Exception
	{
	
		ResultSet res  = null;
		if(conn!=null&&!conn.isClosed()){
			Statement state = conn.createStatement();
			res =  state.executeQuery(sql);
		}
		return res;
	}
	/**
	 * 更新操作  增 删 改
	 * @param connection
	 * @param sql
	 * @return count  sql结果的行数
	 * @throws Exception
	 */
	public  int getUpdate( Connection connection ,String sql) throws Exception
	{
		int count  = 0;
		if( connection!=null&& !connection.isClosed() ){
			Statement statement = connection.createStatement();
			count  = statement.executeUpdate(sql);       
		}
		return count;
	} 
	/**
	 * 关闭连接
	 * @param conn
	 * @throws Exception
	 */
	public void ConnectionClose(Connection conn) throws Exception
	{
		if(conn!=null&& !conn.isClosed()){
			conn.close();
			conn = null; 
		}
	}
}
