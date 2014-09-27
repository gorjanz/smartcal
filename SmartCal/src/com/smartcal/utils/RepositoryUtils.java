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
			usr.setEmail((String) row.get(User.COLUMN_EMAIL));
			usr.setPassword((String) row.get(User.COLUMN_PASSWORD));
			String[] date = ((String) row.get(User.COLUMN_SIGNUPDATE))
					.split("-");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			usr.setSignUpDate(new Date(year, month, day));
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

			String[] startdate = ((String) row.get(Event.COLUMN_STARTIME))
					.split("-");
			int year = Integer.parseInt(startdate[0]);
			int month = Integer.parseInt(startdate[1]);
			int day = Integer.parseInt(startdate[2]);
			evt.setStartTime(new Date(year, month, day));

			String[] enddate = ((String) row.get(Event.COLUMN_ENDTIME))
					.split("-");
			year = Integer.parseInt(enddate[0]);
			month = Integer.parseInt(enddate[1]);
			day = Integer.parseInt(enddate[2]);
			evt.setEndTime(new Date(year, month, day));

			events.add(evt);
		}
		return events;
	}

}
