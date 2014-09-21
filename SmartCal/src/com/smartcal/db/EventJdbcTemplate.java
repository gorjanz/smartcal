package com.smartcal.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public class EventJdbcTemplate implements EventsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void addEvent(Event evt) {
		String sql = "insert into smartcaldb.events (title,description,url,starttime,endtime) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, evt.getTitle(), evt.getDescription(), evt.getUrl(), evt.getStartTime(), evt.getEndTime());
		return;
	}

	@Override
	public void removeEvent(Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEvent(int id, Event newEvt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attend(User usr, Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAttendies(Event evt) {
		// TODO Auto-generated method stub
		return null;
	}
}
