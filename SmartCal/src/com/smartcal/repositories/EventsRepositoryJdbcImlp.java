package com.smartcal.repositories;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public class EventsRepositoryJdbcImlp implements EventsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addEvent(Event evt) {
		String sql = "insert into smartcaldb.`events` (title, description, url, starttime, endtime) values (?,?,?,?,?);";
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		jdbcTemplate.update(sql, evt.getTitle(), evt.getDescription(),
				evt.getUrl(), formatter.format(evt.getStartTime()),
				formatter.format(evt.getEndTime()));
		return;
	}
	
	@Override
	public void updateEvent(int id, Event evt) {
		String sql = "update smartcaldb.events set title = ?, description = ?, " 
				+ " url = ?, starttime = ?, endtime = ? where `events`.eventid = ?;";
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		jdbcTemplate.update(sql, evt.getTitle(), evt.getDescription(),
				evt.getUrl(), formatter.format(evt.getStartTime()),
				formatter.format(evt.getEndTime()), id);
		return;
	}

	@Override
	public void removeEvent(Event evt) {
		String sql = "delete from smartcaldb.events where smartcaldb.events.eventid = ?;";
		jdbcTemplate.update(sql, evt.getEventId());
		return;
	}

	@Override
	public Event getById(int evtId) {
		String sql = "select * from smartcaldb.events where events.eventid = ?;";
		Event evt = (Event) jdbcTemplate.queryForObject(sql, new Object[] {evtId}, new EventMapper());
		return evt;
	}

	@Override
	public void attend(User usr, Event evt) {
		String sql = "insert into smartcaldb.attending (uid,eid) values (?,?);";
		jdbcTemplate.update(sql, usr.getUserId(), evt.getEventId());
		return;
	}

	@Override
	public List<User> getAttendies(Event evt) {
		String sql = "select users.*" 
				+ "from smartcaldb.users, smartcaldb.attending"
				+ "where users.userid = attending.uid and attending.eid = ?;";
		List<User> users = new ArrayList<User>();
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql, evt.getEventId());
		
		User usr = null;
		for (Map<String, Object> row : rows) {
			usr = new User();
			usr.setUserId(Integer.parseInt(String.valueOf(row.get("userid"))));
			usr.setName((String)row.get("name"));
			usr.setEmail((String)row.get("email"));
			usr.setPassword((String)row.get("password"));
			String[] date = ((String)row.get("signupdate")).split("-");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			usr.setSignUpDate(new Date(year,month,day));
			users.add(usr);
		}
		return users;
	}
}
