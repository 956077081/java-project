package com.dinner.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


public class Form implements Serializable {
	
	private int fid;        //����id
	private int deid;       //����id
	private String dname;   //������
	private Timestamp fdate;//����ʱ��
	private Timestamp fdate2;//����ʱ��
	private int fsdate;//����״̬
	private double total;//�ܼ�
	private int aid;//�µ���
	private String aname;//�µ����˺�
	private double fincome;//ʵ������
	private double fsum;//�ݴ�Ӧ��
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
