package com.smartcal.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.smartcal.models.User;

public class RepositoryUtils {
	
	public static List<User> generateResultList(List<Map<String, Object>> rows){
		List<User> users = new ArrayList<User>();
		
		User usr = null;
		for (Map<String, Object> row : rows) {
			usr = new User();
			usr.setUserId(Integer.parseInt(String.valueOf(row.get(User.COLUMN_USERID))));
			usr.setName((String) row.get(User.COLUMN_NAME));
			usr.setEmail((String) row.get(User.COLUMN_EMAIL));
			usr.setPassword((String) row.get(User.COLUMN_PASSWORD));
			String[] date = ((String) row.get(User.COLUMN_SIGNUPDATE)).split("-");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			usr.setSignUpDate(new Date(year, month, day));
			users.add(usr);
		}
		return users;		
	}

}
