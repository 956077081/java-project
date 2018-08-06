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
		this.total = total; //桌子总数
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	//得到总页数
	/**
	 * 得到总页数
	 * @return int  
	 */
	  public int getCount(){  //强转为int 类型时  当total小于pagesize -1时 出现错误 页面为 0 保证有数据时凑齐每一页保证 取整时  不会出现page 少一
		  System.out.println(this.total);
		  return (this.total+this.pageSize-1)/this.pageSize;
	  }
	  /**
	   * 返回首页
	   * @return
	   */
	  public int getFirst(){
		  
		  return 1;
	  }
	  /**
	   * 返回尾页
	   * 
	   * @return  
	   */
	  public int getLast(){
		  return  this.getCount();
	  }
	  /**
	   * 返回上一页
	   * @return
	   */
	  public int getUp(){
		  return  this.pageNum==1?1:this.pageNum-1;
	  }
  /**
   * 返回下一页
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
