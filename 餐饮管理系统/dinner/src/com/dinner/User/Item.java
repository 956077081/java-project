package com.dinner.User;

import java.math.BigDecimal;

//点菜单
public class Item {
	private int fid; //订单id
	private int inum;  // 菜品的数目
	private double sum; //  花费总数
	private Cuisine cuisine; //菜品
	public Item(int fid, int inum, double sum, Cuisine cuisine) {
		super();
		this.fid = fid;
		this.inum = inum;
		this.sum = sum;
		this.cuisine = cuisine;
	}
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
		//每当数目修改时重置 价格
		double s1   = this.cuisine.getCprice();
		BigDecimal bd = new BigDecimal(s1);
		this.sum = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public Cuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}
	
}
