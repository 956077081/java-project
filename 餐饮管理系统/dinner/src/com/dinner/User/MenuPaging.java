package com.dinner.User;

public class MenuPaging { //用于查找菜品
 private Double  price1;
 private Double price2;
 private String cname ;
 private String ctype;
public MenuPaging(Double price1, Double price2, String cname, String ctype) {
	super();
	this.price1 = price1;
	this.price2 = price2;
	this.cname = cname;
	this.ctype = ctype;
}
public Double getPrice1() {
	return price1;
}
public void setPrice1(Double price1) {
	this.price1 = price1;
}
public Double getPrice2() {
	return price2;
}
public void setPrice2(Double price2) {
	this.price2 = price2;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getCtype() {
	return ctype;
}
public void setCtype(String ctype) {
	this.ctype = ctype;
}

}
