package com.example.demo.model;

public class UserModel {
	private Integer id;
	private String name;
	private String accountId;
	private String token;
	
	private long gmtModified;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	public long getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(long gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	
}
