package com.dinner.User;

import java.math.BigDecimal;

//��˵�
public class Item {
	private int fid; //����id
	private int inum;  // ��Ʒ����Ŀ
	private double sum; //  ��������
	private Cuisine cuisine; //��Ʒ
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
		//ÿ����Ŀ�޸�ʱ���� �۸�
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
