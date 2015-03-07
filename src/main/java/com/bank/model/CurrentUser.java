package com.bank.model;

public class CurrentUser {

	private String username;
	private String password;
	
	private static String userType;
	private static String currentUser;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public static String getCurrentUser() {
		return currentUser;
	}
	public static void setCurrentUser(String currentUser) {
		CurrentUser.currentUser = currentUser;
	}
	public static String getUserType() {
		return userType;
	}
	public static void setUserType(String userType) {
		CurrentUser.userType = userType;
	}
}
