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

@Repository
public class EventsRepositoryJdbcImlp implements EventsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

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
		Event evt = (Event) jdbcTemplate.queryForObject(sql,
				new Object[] { evtId }, new EventMapper());
		return evt;
	}

	@Override
	public List<Event> getEvents() {
		String sql = "select distinct e.* from smartcaldb.events as e;";
		return RepositoryUtils.generateEventResultList(jdbcTemplate
				.queryForList(sql));
	}

	@Override
	public void attend(int usrId, int evtId) {
		String sql = "insert into smartcaldb.attending (uid,eid) values (?,?);";
		jdbcTemplate.update(sql, usrId, evtId);
		return;
	}

	@Override
	public void cancel(int usrId, int evtId) {
		String sql = "delete from smartcaldb.attending where smartcaldb.attending.uid = ? and "
				+ "smartcaldb.attending.eid = ?";
		jdbcTemplate.update(sql, usrId, evtId);
		return;
	}

	@Override
	public List<Event> getPastEvents(int usrId) {
		String sql = "select e.*"
				+ "from smartcaldb.events as e, smartcaldb.attending as a"
				+ "where a.uid = ? and a.eid = e.eventid and e.starttime <= '?'";
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

		return RepositoryUtils
				.generateEventResultList(jdbcTemplate.queryForList(sql, usrId,
						formatter.format(new Date(System.currentTimeMillis()))));
	}

	@Override
	public List<Event> getIncomingEvents(int usrId) {
		String sql = "select e.*"
				+ "from smartcaldb.events as e, smartcaldb.attending as a"
				+ "where a.uid = ? and a.eid = e.eventid and e.starttime >= '?'";
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

		return RepositoryUtils
				.generateEventResultList(jdbcTemplate.queryForList(sql, usrId,
						formatter.format(new Date(System.currentTimeMillis()))));
	}

	@Override
	public List<Event> getByDate(Date date) {
		String sql = "select e.*" + "from smartcaldb.events as e"
				+ "where e.starttime = '?'";
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

		return RepositoryUtils.generateEventResultList(jdbcTemplate
				.queryForList(sql, formatter.format(date)));
	}

}
