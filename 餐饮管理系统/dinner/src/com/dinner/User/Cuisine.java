package com.dinner.User;

//≤Àµ•π‹¿Ì
public class Cuisine {
	  private static final long serialVersionUID=1L;
	  private int cid;
	  private String cname;
	  private double ccost;
	  private double cprice;
	  private String cimg;
	  private String cinfo;
	  private int cexist;
	  private int mid;
	  private String mtype;
	  
	public Cuisine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cuisine(int cid, String cname, double ccost, double cprice,
			String cimg, String cinfo, int cexist, int mid, String mtype) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.ccost = ccost;
		this.cprice = cprice;
		this.cimg = cimg;
		this.cinfo = cinfo;
		this.cexist = cexist;
		this.mid = mid;
		this.mtype = mtype;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public double getCcost() {
		return ccost;
	}
	public void setCcost(double ccost) {
		this.ccost = ccost;
	}
	public double getCprice() {
		return cprice;
	}
	public void setCprice(double cprice) {
		this.cprice = cprice;
	}
	public String getCimg() {
		return cimg;
	}
	public void setCimg(String cimg) {
		this.cimg = cimg;
	}
	public String getCinfo() {
		return cinfo;
	}
	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}
	public int getCexist() {
		return cexist;
	}
	public void setCexist(int cexist) {
		this.cexist = cexist;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	  
	  
	  
}
