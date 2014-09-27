package com.smartcal.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smartcal.models.Event;

public class EventMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet resSet, int rowNum) throws SQLException {
		Event evt = new Event();
		evt.setEventId(resSet.getInt("eventid"));
		evt.setTitle(resSet.getString("title"));
		evt.setDescription(resSet.getString("description"));
		evt.setUrl(resSet.getString("url"));
		evt.setStartTime(resSet.getDate("starttime"));
		evt.setStartTime(resSet.getDate("endtime"));
		return evt;
	}
	
}
