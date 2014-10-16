package com.smartcal.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public class RepositoryUtils {

	public static List<User> generateUserResultList(
			List<Map<String, Object>> rows) {
		List<User> users = new ArrayList<User>();

		User usr = null;
		for (Map<String, Object> row : rows) {
			usr = new User();
			usr.setUserId(Integer.parseInt(String.valueOf(row
					.get(User.COLUMN_USERID))));
			usr.setName((String) row.get(User.COLUMN_NAME));
			usr.setUserName((String)row.get(User.COLUMN_USERNAME));
			usr.setEmail((String) row.get(User.COLUMN_EMAIL));
			usr.setPassword((String) row.get(User.COLUMN_PASSWORD));
			usr.setSignUpDate((Date) row.get(User.COLUMN_SIGNUPDATE));
			users.add(usr);
		}
		return users;
	}

	public static List<Event> generateEventResultList(
			List<Map<String, Object>> rows) {
		List<Event> events = new ArrayList<Event>();

		Event evt = null;
		for (Map<String, Object> row : rows) {
			evt = new Event();
			evt.setEventId(Integer.parseInt(String.valueOf(row
					.get(Event.COLUMN_EVENTID))));
			evt.setTitle((String) row.get(Event.COLUMN_TITLE));
			evt.setDescription((String) row.get(Event.COLUMN_DESCRIPTION));
			evt.setUrl((String) row.get(Event.COLUMN_URL));
			evt.setCategory((String) row.get(Event.COLUMN_CATEGORY));
			evt.setStartTime((Date) row.get(Event.COLUMN_STARTIME));
			evt.setEndTime((Date) row.get(Event.COLUMN_ENDTIME));
			
			events.add(evt);
		}
		return events;
	}

}
