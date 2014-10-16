package com.smartcal.repositories;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smartcal.models.Event;
import com.smartcal.utils.RepositoryUtils;
import com.smartcal.utils.SQLQueries;

@Repository
public class EventsRepositoryJdbcImlp implements EventsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addEvent(Event evt) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		jdbcTemplate.update(SQLQueries.SQL_ADD_EVENT, evt.getTitle(),
				evt.getDescription(), evt.getUrl(),
				formatter.format(evt.getStartTime()),
				formatter.format(evt.getEndTime()));
		return;
	}

	@Override
	public void updateEvent(int id, Event evt) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		jdbcTemplate.update(SQLQueries.SQL_UPDATE_EVENT, evt.getTitle(),
				evt.getDescription(), evt.getUrl(),
				formatter.format(evt.getStartTime()),
				formatter.format(evt.getEndTime()), id);
		return;
	}

	@Override
	public void removeEvent(Event evt) {
		jdbcTemplate.update(SQLQueries.SQL_REMOVE_EVENT, evt.getEventId());
		return;
	}

	@Override
	public Event getById(int evtId) {
		Event evt = (Event) jdbcTemplate.queryForObject(
				SQLQueries.SQL_GET_EVENT_BY_ID, new Object[] { evtId },
				new EventMapper());
		return evt;
	}

	@Override
	public List<Event> getEvents() {
		return RepositoryUtils.generateEventResultList(jdbcTemplate
				.queryForList(SQLQueries.SQL_GET_ALL_EVENTS));
	}

	@Override
	public void attend(int usrId, int evtId) {
		jdbcTemplate.update(SQLQueries.SQL_USER_EVENT_ATTEND, usrId, evtId);
		return;
	}

	@Override
	public void cancel(int usrId, int evtId) {
		jdbcTemplate.update(SQLQueries.SQL_USER_EVENT_CANCEL, usrId, evtId);
		return;
	}

	@Override
	public List<Event> getPastEvents(int usrId) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return RepositoryUtils
				.generateEventResultList(jdbcTemplate.queryForList(
						SQLQueries.SQL_GET_PAST_EVENTS_FOR_USER, usrId,
						formatter.format(new Date(System.currentTimeMillis()))));
	}

	@Override
	public List<Event> getIncomingEvents(int usrId) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return RepositoryUtils
				.generateEventResultList(jdbcTemplate.queryForList(
						SQLQueries.SQL_GET_UPCOMING_EVENTS_FOR_USER, usrId,
						formatter.format(new Date(System.currentTimeMillis()))));
	}

	@Override
	public List<Event> getByDate(Date date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return RepositoryUtils.generateEventResultList(jdbcTemplate
				.queryForList(SQLQueries.SQL_GET_EVENTS_BY_DATE,
						formatter.format(date)));
	}

}
