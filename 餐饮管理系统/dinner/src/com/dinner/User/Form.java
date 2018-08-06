package com.dinner.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


public class Form implements Serializable {
	
	private int fid;        //订单id
	private int deid;       //餐桌id
	private String dname;   //餐桌名
	private Timestamp fdate;//开单时间
	private Timestamp fdate2;//结账时间
	private int fsdate;//结账状态
	private double total;//总价
	private int aid;//下单人
	private String aname;//下单人账号
	private double fincome;//实际收入
	private double fsum;//暂存应收
	private Map<Integer,Item> items=new HashMap<Integer,Item>();
	public Form() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getDeid() {
		return deid;
	}
	public void setDeid(int deid) {
		this.deid = deid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Timestamp getFdate() {
		return fdate;
	}
	public void setFdate(Timestamp fdate) {
		this.fdate = fdate;
	}
	public Timestamp getFdate2() {
		return fdate2;
	}
	public void setFdate2(Timestamp fdate2) {
		this.fdate2 = fdate2;
	}
	public int getFsdate() {
		return fsdate;
	}
	public void setFsdate(int fsdate) {
		this.fsdate = fsdate;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public double getFincome() {
		return fincome;
	}
	public void setFincome(double fincome) {
		this.fincome = fincome;
	}
	public double getFsum() {
		return fsum;
	}
	public void setFsum(double fsum) {
		this.fsum = fsum;
	}
	public Map<Integer, Item> getItems() {
		return items;
	}
	public void setItems(Map<Integer, Item> items) {
		this.items = items;
	}
	

}
