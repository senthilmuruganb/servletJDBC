package com.vit.model;

public class User {
	
	private String username;
	private String email;
	private String password;
	private int age;
	private String userId;
	public User(String username, String email, String password, int age, String userId) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.age = age;
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password + ", age=" + age
				+ ", userId=" + userId + "]";
	}
	
	

}
