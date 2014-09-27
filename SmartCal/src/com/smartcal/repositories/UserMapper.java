package com.smartcal.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smartcal.models.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resSet, int rowNum) throws SQLException {
		User usr = new User();
		usr.setUserId(resSet.getInt(User.COLUMN_USERID));
		usr.setName(resSet.getString(User.COLUMN_NAME));
		usr.setEmail(resSet.getString(User.COLUMN_EMAIL));
		usr.setSignUpDate(resSet.getDate(User.COLUMN_SIGNUPDATE));
		usr.setUserName(resSet.getString(User.COLUMN_USERNAME));
		usr.setPassword(resSet.getString(User.COLUMN_PASSWORD));
		return usr;
	}
}
