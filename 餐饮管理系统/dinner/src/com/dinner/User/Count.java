package com.dinner.User;

import java.sql.Timestamp;



public class Count {
private int fid ;
private String username ;
private double fincome ;
private  Timestamp fdate2 ;
private String cuisine ;
private int counts;
public int getFid() {
	return fid;
}
public void setFid(int fid) {
	this.fid = fid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public double getFincome() {
	return fincome;
}
public void setFincome(double fincome) {
	this.fincome = fincome;
}
public Timestamp getFdate2() {
	return fdate2;
}
public void setFdate2(Timestamp fdate2) {
	this.fdate2 = fdate2;
}
public String getCuisine() {
	return cuisine;
}
public void setCuisine(String cuisine) {
	this.cuisine = cuisine;
}
public int getCounts() {
	return counts;
}
public void setCounts(int counts) {
	this.counts = counts;
}

}