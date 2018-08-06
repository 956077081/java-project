package com.dinner.User;

public class User {
	public static final long USERVERSION = 1L; //版本
	private int id;
	private String username ;
	private String password ;
	private int type;
	private int exist;
	//两种构造
	public User(int id, String username, String password, int type, int exist) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
		this.exist = exist;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getExist() {
		return exist;
	}
	public void setExist(int exist) {
		this.exist = exist;
	}
	public static long getVersion(){
		return  USERVERSION;
	}


}
