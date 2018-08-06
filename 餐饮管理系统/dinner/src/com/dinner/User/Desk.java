package com.dinner.User;

import java.io.Serializable;

public class Desk  implements Serializable{ //允许实例化保存
	public static final long serialVersionUID=1L;
	private int did;
	private String dname;
	private int dexist;
	private int dstatus;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDexist() {
		return dexist;
	}
	public void setDexist(int dexist) {
		this.dexist = dexist;
	}
	public int getDstatus() {
		return dstatus;
	}
	public void setDstatus(int dstatus) {
		this.dstatus = dstatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
