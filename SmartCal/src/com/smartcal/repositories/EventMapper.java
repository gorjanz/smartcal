package com.smartcal.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smartcal.models.Event;

public class EventMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet resSet, int rowNum) throws SQLException {
		Event evt = new Event();
		evt.setEventId(resSet.getInt(Event.COLUMN_EVENTID));
		evt.setTitle(resSet.getString(Event.COLUMN_TITLE));
		evt.setDescription(resSet.getString(Event.COLUMN_DESCRIPTION));
		evt.setUrl(resSet.getString(Event.COLUMN_URL));
		evt.setStartTime(resSet.getTimestamp(Event.COLUMN_STARTIME));
		evt.setStartTime(resSet.getTimestamp(Event.COLUMN_ENDTIME));
		evt.setCategory(resSet.getString(Event.COLUMN_CATEGORY));
		return evt;
	}
	
}
