package com.smartcal.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smartcal.models.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resSet, int rowNum) throws SQLException {
		User usr = new User();
		usr.setUserId(resSet.getInt("userid"));
		usr.setName(resSet.getString("name"));
		usr.setEmail(resSet.getString("email"));
		usr.setSignUpDate(resSet.getDate("signupdate"));
		usr.setPassword(resSet.getString("password"));
		return usr;
	}
}
