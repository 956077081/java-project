package com.dinner.User;

public class Menutype {
	private int  mid ;   //�˵�id
	private String mtype ; //������
	private  int mexist;  //״̬
	private  int status;//�Ƿ��Ʒ�и�����
	public Menutype() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menutype(int mid, String mtype, int mexist) {
		super();
		this.mid = mid;
		this.mtype = mtype;
		this.mexist = mexist;
	}
	public int getmid() {
		return mid;
	}
	public void setmid(int mid) {
		this.mid = mid;
	}
	public String getmtype() {
		return mtype;
	}
	public void setmtype(String mtype) {
		this.mtype = mtype;
	}
	public int getmexist() {
		return mexist;
	}
	public void setmexist(int mexist) {
		this.mexist = mexist;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
 }
