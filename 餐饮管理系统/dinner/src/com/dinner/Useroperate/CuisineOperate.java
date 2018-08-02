package com.dinner.Useroperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.dinner.Until.DBUtil;
import com.dinner.User.Cuisine;
import com.dinner.User.MenuPaging;
import com.dinner.comm.CommFunc;

/**
 * 
 * @author lenovo-pc
 *
 */
public class CuisineOperate {
	private Connection conn = null;
	private PreparedStatement pst = null;
	ResultSet res = null;
	/**
	 * 返回当前页的所有菜单
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Cuisine> findAllByPage(int pageNum,int pageSize)
	{
		List<Cuisine> list = new ArrayList<Cuisine>();
		conn =  DBUtil.getConnection();
		String sql  = " select *"
				+ " from ( select c.*,m.m_type from cuisine c left join menuType m on c.m_id=m.m_id order by c_id desc) cui "
				+ "limit ?,?";
		try {
			pst = conn.prepareStatement(sql);
			int start = (pageNum - 1 )*pageSize;
			int end  = (pageNum)*pageSize;
			pst.setInt(1, start);
			pst.setInt(2, end-start);
			
			res = pst.executeQuery();
			while(res.next()){
				Cuisine cuisine  = new Cuisine();
				cuisine.setCid(res.getInt(1));
				cuisine.setCname(res.getString(2));
				cuisine.setCcost(res.getDouble(3));
				cuisine.setCprice(res.getDouble(4));
				cuisine.setCimg(res.getString(5));
				cuisine.setCinfo(res.getString(6));
				cuisine.setMid(res.getInt(7));
				cuisine.setCexist(res.getInt(8));
				cuisine.setMtype(res.getString(9));
				list.add(cuisine);
			}
		} catch (Exception e) {
			System.out.println("error");
		}finally{
			DBUtil.closeConn(conn);
		}
		return list;
	}
	/**
	 * 返回菜单总数
	 * @return
	 */
	public int getCount(){
		int count  = 0 ;
		String sql = "select count(*)  from cuisine ";
		conn  = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			if(res.next()){
				count  = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 
	 * @param cexist
	 * @param cid
	 * @return
	 */
	public boolean setExist(int cexist ,int cid){
		int temp  = 0;
		System.out.println(cexist);
		System.out.println(cid);
	   conn	 = DBUtil.getConnection();
	   String sqlString  = " update cuisine  set  c_exist = ? where c_id = ? ";
	   System.out.println(sqlString);
	   try {
		pst = conn.prepareStatement(sqlString);
		pst.setString(1, String.valueOf(cexist));
		pst.setInt(2,cid);
		System.out.println(pst.toString());
		temp  = pst.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		DBUtil.closeConn(conn);
	}
	   return temp>0?true:false ;
}
	/**
	 * 
	 * @param cname
	 * @param ccost
	 * @param cprice
	 * @param cimg
	 * @param mid
	 * @param cinfo
	 * @return
	 */
	public boolean insertCuisine(String cname,double ccost,double cprice,String cimg,int mid,String cinfo){
		System.out.println(mid+"   asdad     "+cinfo);
		int temp = 0;
		conn  = DBUtil.getConnection();
		String sql = "  insert into cuisine(c_name,c_cost,c_price,c_img,c_info,m_id,c_exist) values (?,?,?,?,?,?,'1')";
		try {
			pst= conn.prepareStatement(sql);
			pst.setString(1, cname);
			pst.setDouble(2, ccost);
			pst.setDouble(3, cprice);
			pst.setString(4, cimg);
			pst.setString(5, cinfo);
			pst.setInt(6, mid);
			temp =  pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("error_insert");
		}
		System.err.println(temp);
		return temp>0?true:false;
	}
	/**
	 * 检查是否存在重名
	 * @param cname
	 * @return
	 */
	public boolean checkName(String cname){
		int count = 0;
		conn = DBUtil.getConnection();
	   String sql = "select count(*) from cuisine where c_name = ?";
	   System.out.println("aaaa");
	   try {
			
		pst = conn.prepareStatement(sql);
		System.out.println("aaaa");
		pst.setString(1, cname);
	
		System.out.println(pst.toString());
		res = pst.executeQuery();
		if(res.next()){
			count = res.getInt(1);
		}
	} catch (Exception e) {
			System.out.println("error");
	}
    return count>0 ?true:false;
		
	}
	/**
	 * 
	 * @param cname
	 * @return
	 */
	public Cuisine checkCid(int cid){
		int count = 0;
		conn = DBUtil.getConnection();
	   String sql = "select c_id,c_name,c_cost,c_price,c_img ,c_info from cuisine where c_id = ?";
	   Cuisine cuisine = new Cuisine();
	   try {
			
		pst = conn.prepareStatement(sql);
		System.out.println(pst.toString());
		pst.setInt(1, cid);
		System.out.println(pst.toString());
		res = pst.executeQuery();
		if(res.next()){
			
			cuisine.setCid(res.getInt(1));
			cuisine.setCname(res.getString(2));
			cuisine.setCcost(res.getDouble(3));
			cuisine.setCprice(res.getDouble(4));
			cuisine.setCimg(res.getString(5));
			cuisine.setCinfo(res.getString(6));
		}
		} catch (Exception e) {
			System.out.println("errorcheckcid");
		}
			
	    return cuisine;
 
	}
	/**
	 * 
	 * @param mid
	 * @param cprice
	 * @param info
	 * @return
	 */
	public boolean updateCuisine(int cid ,int mid ,double cprice,String info) {
		int count = 0;
		conn  = DBUtil.getConnection();
		String sql = " update cuisine set   m_id = ? , c_price = ? , c_info  = ? where c_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, mid);
			pst.setDouble(2, cprice);
			pst.setString(3, info.trim());
			pst.setInt(4,cid);
			System.out.println(pst.toString());
			count = pst.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("error");
		}
		
		return count>0 ?true :false;

	}
	public List<Cuisine> pageCuisines(int pageNum,int pageSize ,MenuPaging mp)
	{
			List<Cuisine> list  = new ArrayList<Cuisine>();
			conn =DBUtil.getConnection();
			String sql =" select * "+
					" from ( select c.* ,m.m_type "+
							" from cuisine c  ,menutype m "+ 
							" where   c.m_id = m.m_id "+
							" order by c_id "+
							"	) rn where  c_exist = '1'  ";
			if(mp.getPrice1()>0  && mp.getPrice2() == 0){ //价格等于 price1
				sql += " and c_price = "+ mp.getPrice1();
			}
			if(mp.getPrice1()==0 && mp.getPrice2()>0){//等于 price2
				sql += " and c_price = "+ mp.getPrice2();
			}
			if(mp.getPrice1()>0&&mp.getPrice2()>0&&mp.getPrice1()<mp.getPrice2()){ //查询价格之间
				sql+=" and c_price between "+mp.getPrice1()+" and "+mp.getPrice2();
			}
			if(mp.getPrice1()>0&&mp.getPrice2()>0&&mp.getPrice1()>mp.getPrice2()){ //格式错误按名字查 
				sql+=" and c_name like '%"+mp.getCname()+"%' ";
			}
			if(CommFunc.isNotEmpty(mp.getCtype())){
				sql+=" and m_type like '%"+mp.getCtype()+"%' ";
				
			}
			if(CommFunc.isNotEmpty(mp.getCname())){
				sql+=" and c_name like '%"+mp.getCname()+"%' ";
				
			}
			
			String sql2 = sql+" limit ?,? ";
			System.out.println(sql);
			try {
				pst = conn.prepareStatement(sql2);
				int start = (pageNum - 1 )*pageSize;
				pst.setInt(1,start);
				pst.setInt(2, pageSize);
				res = pst.executeQuery();
				while(res.next()){
					Cuisine cuisine=new Cuisine();
					cuisine.setCid(res.getInt(1));
					cuisine.setCname(res.getString(2));
					cuisine.setCcost(res.getDouble(3));
					cuisine.setCprice(res.getDouble(4));
					cuisine.setCimg(res.getString(5));
					cuisine.setCinfo(res.getString(6));
					cuisine.setMid(res.getInt(7));
					cuisine.setCexist(res.getInt(8));
					cuisine.setMtype(res.getString(9));
					list.add(cuisine);
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("pageCuisineerror");
			}finally{
				DBUtil.closeConn(conn);
			}
			return  list;
	}
	public int getCountbyprice(MenuPaging mp)
	{
		int count = 0;
		String sql = " select count(*) from cuisine c,menutype m where c.c_exist='1' and c.m_id=m.m_id ";
		if(mp.getPrice1()>0&&mp.getPrice2()==0){
			sql+=" and c.c_price="+mp.getPrice1();
		}
		if(mp.getPrice1()==0&&mp.getPrice2()>0){
			sql+=" and c.c_price="+mp.getPrice2();
		}
		
		if(mp.getPrice1()>0&&mp.getPrice2()>0&&mp.getPrice1()<mp.getPrice2()){
			sql+=" and c.c_price between "+mp.getPrice1()+" and "+mp.getPrice2();
		}
		if(mp.getPrice1()>0&&mp.getPrice2()>0&&mp.getPrice1()>mp.getPrice2()){
			sql+=" and c.c_price between "+mp.getPrice2()+" and "+mp.getPrice1();
		}
		if(CommFunc.isNotEmpty(mp.getCname())){
			sql+=" and c.c_name like '%"+mp.getCname()+"%' ";	
		}
		if(CommFunc.isNotEmpty(mp.getCtype())){
			sql+=" and m.m_type like '%"+mp.getCtype()+"%' ";	
		}
		System.out.println(sql);
		conn = DBUtil.getConnection();
		try {
		pst = conn.prepareStatement(sql);
		res = pst.executeQuery();
		if(res.next()){
			count = res.getInt(1);
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("getcountpricERROR");
		}
		return count;
	}
	
	
	
}
