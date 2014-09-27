package com.smartcal.models;

import java.sql.Date;

/**
 * 
 * @author Gorjan
 *	Model class for the relation users(userid,name,email,signupdate,username,password)
 */
public class User {

	public static final int NOT_PERSISTED = -1;
	
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_SIGNUPDATE = "signupdate";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	
	private int userId;
	private String name;
	private String userName;
	private String email;
	private String password;
	private Date signUpDate;

	public void setUserId(int id) {
		userId = id;
	}

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		User otherUser = (User) obj;
		return this.userId == otherUser.getUserId();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User ID: " + this.userId + "\n");
		sb.append("Name: " + this.name + "\n");
		sb.append("Username: " + this.userName + "\n");
		sb.append("Password: " + this.password + "\n");
		sb.append("Email: " + this.email + "\n");
		sb.append("Member since: " + this.signUpDate.toString() + "\n");
		return sb.toString();
	}

}
