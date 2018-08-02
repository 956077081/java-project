package com.dinner.User;

import java.util.ArrayList;
import java.util.List;

	public class Page<T> {
	  private static final long serialVersionUID=1L;
	  private int pageNum ;
	  private  int pageSize;
	  private List<T> data =  new ArrayList<T>();
	  private int total;

	public Page(int pageNum, int pageSize, List<T> data, int total) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.data = data;
		this.total = total; //��������
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	//�õ���ҳ��
	/**
	 * �õ���ҳ��
	 * @return int  
	 */
	  public int getCount(){  //ǿתΪint ����ʱ  ��totalС��pagesize -1ʱ ���ִ��� ҳ��Ϊ 0 ��֤������ʱ����ÿһҳ��֤ ȡ��ʱ  �������page ��һ
		  System.out.println(this.total);
		  return (this.total+this.pageSize-1)/this.pageSize;
	  }
	  /**
	   * ������ҳ
	   * @return
	   */
	  public int getFirst(){
		  
		  return 1;
	  }
	  /**
	   * ����βҳ
	   * 
	   * @return  
	   */
	  public int getLast(){
		  return  this.getCount();
	  }
	  /**
	   * ������һҳ
	   * @return
	   */
	  public int getUp(){
		  return  this.pageNum==1?1:this.pageNum-1;
	  }
  /**
   * ������һҳ
   * @return
   */
  	public int getDown(){
  		return this.pageNum==this.getLast()?this.pageNum:pageNum+1;
  	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
 
}
