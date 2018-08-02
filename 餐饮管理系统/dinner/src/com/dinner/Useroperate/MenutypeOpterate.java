package com.dinner.Useroperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.xml.transform.Templates;

import com.dinner.Until.DBUtil;
import com.dinner.User.Menutype;
/**
 * 
 * @author lenovo-pc
 *
 */
public class MenutypeOpterate {
	Connection    conn    = null ;
	PreparedStatement ps  = null ;
	ResultSet     rs      = null ;
	/**
	 * �õ��˵���������
	 * @return
	 */
	public int getCountMenu(){
		int count = 0;
		conn = DBUtil.getConnection();
	
		String sql = " select count(*) from menutype";
		try {
			ps = conn.prepareStatement(sql);
			rs =  ps.executeQuery();
			if( rs.next() ){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("error-menutypeOpterate-getCountMenu");
		}
		return count;
	}
	
	/**
	 * �õ����еĲ˵�����
	 * @param pageNum   ҳ��
	 * @param pageSize ҳ���С
	 * @param type  0����ͣ��  1 ��������
	 * @return
	 */
	public List<Menutype> ListMenutype(int pageNum , int pageSize  ,int type ){
		
		
		//�������еĲ˵�����
		List<Menutype> listmenu  =  new  ArrayList<Menutype>();
		conn = DBUtil.getConnection();
		//���ҳ����е� 
		//String sql =  " select * from menutype";
		String sql  = " select  * from (select t.* "
								+ " from  (select m.m_id,m.m_type,m.m_exist ,count(c.c_id) as c_status "
										+ " from menutype m left join cuisine c on c.m_id = m.m_id  and c_exist = '1' "
										+ " group by m.m_id ,m.m_type,m.m_exist  ) t "
								+ " ) rs ";
		
					 
		if(type == 0){
			sql +=  " where m_exist  = '0'";
		}else if(type == 1){
			sql +=  " where m_exist  = '1' ";
		}
		sql +=  " limit ?,?";
		System.out.println(sql);
		try {
	
			ps = conn.prepareStatement(sql);
			int start  = (pageNum-1)*pageSize; //��ʼλ��
			int length = pageSize; //��ѯ����
			ps.setInt(1, start);
			ps.setInt(2, length);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			
			while( rs.next() ){
				Menutype menutype = new  Menutype();
				menutype.setmid(rs.getInt(1) );
				menutype.setmtype(rs.getString(2));
				menutype.setmexist(rs.getInt(3) );
				menutype.setStatus(rs.getInt(4));//
				listmenu.add(menutype);
			}
		} catch (Exception e) {
			
			System.out.println("error-menuopterate-Listmenutype");
		}
		return listmenu;
	}
	/**
	 * ���� exist
	 * @param exist
	 * @param mid
	 * @return
	 */
	public boolean setExist( int exist,int mid){
		conn = DBUtil.getConnection();
		
		int temp =  0;
		String sql = "update menutype set `m_exist` = ? where m_id = ?";
		
		try {
			String    existsstring = "";
			ps = conn.prepareStatement(sql); 
		
			if(exist==1){
				existsstring ="1";
			}else{
				existsstring = "0";
			}
			ps.setString(1, existsstring);
			ps.setInt(2, mid);
			System.out.println(mid);
			System.out.println(ps.toString());
			temp  = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(temp);
	   if(temp > 0){
		   return  true;
	   }
	   return false;
	   
	}
	/**
	 * ��������
	 * @param mtype
	 * @return
	 */
	public boolean addmenutype(String mtype){
		//��������
		int temp= 0;
		conn = DBUtil.getConnection();
		String sql = " insert into menutype(m_type,m_exist) values (?,1)";
		try {
			System.out.println(sql);
			ps  =  conn.prepareStatement(sql);
	
			ps.setString(1, mtype);
			temp = ps.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return temp>0?true:false;
	}
	public boolean updateType(int m_id ,String type){
		
		int count = 0;
		//����
		conn = DBUtil.getConnection();
		String sqlcheck = "select count(*) from menutype where m_type = ? ";
		try {
			ps = conn.prepareStatement(sqlcheck);
			ps.setString(1, type);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(count);
		if(count == 0 ){
			//�޸�
			System.out.println("asdadas");
			int effect = 0 ;
			String sql =  "update menutype set m_type = ? where m_id = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, type);
				ps.setInt(2, m_id);
				effect = ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(effect>0){
				return true;
			}
		}
		return false;

	}
	public List<Menutype> showAllType(){
		List<Menutype> list=new ArrayList<Menutype>();
		String sql="select * from menutype where m_exist='1' ";
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Menutype mt=new Menutype();
				mt.setmid(rs.getInt(1));
				mt.setmtype(rs.getString(2));
				mt.setmexist(rs.getInt(3));
				list.add(mt);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeConn(conn);
		}
		return list;
	}
	
}
