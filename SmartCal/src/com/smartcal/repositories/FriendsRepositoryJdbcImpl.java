package com.smartcal.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smartcal.models.Event;
import com.smartcal.models.User;
import com.smartcal.utils.RepositoryUtils;

public class FriendsRepositoryJdbcImpl implements FriendsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> getFriendsFor(User usr) {
		String sql = "select u.*"
				+ "from smartcaldb.friend as f, smartcaldb.users as u"
				+ "where f.user1 = ? and f.user2 = u.userid";
		
		return RepositoryUtils.generateUserResultList(jdbcTemplate.queryForList(sql,usr.getUserId()));
	}

	@Override
	public List<User> getFriendsWithSimilarityAbove(User usr,
			double minSimilarity) {
		
		String sql = "select u.*"
				+ "from smartcaldb.friend as f, smartcaldb.users as u"
				+ "where f.user1 = ? and f.similarity > ? and f.user2 = u.userid";
		
		return RepositoryUtils.generateUserResultList(jdbcTemplate.queryForList(sql,usr.getUserId(),minSimilarity));
	}

	@Override
	public List<User> getFriendsAttending(User theUsr, Event evt) {
		String sql = "select u.*"
		+ "from smartcaldb.events as e, smartcaldb.attending as a,"
		+ " smartcaldb.friend as f, smartcaldb.users as u"
		+ "where e.eventid = ? and f.user1 = ? and a.uid = f.user2 and a.eid = ? and a.uid = u.userid";
		
		return RepositoryUtils.generateUserResultList(jdbcTemplate.queryForList(sql,
				evt.getEventId(), theUsr.getUserId(), evt.getEventId()));
	}
	
	@Override
	public void createFriendship(User usr1, User usr2, double similarity) {
		String sql = "insert into smartcaldb.friend (user1, user2, similarity) values (?,?,?);";
		jdbcTemplate.update(sql, usr1.getUserId(), usr2.getUserId(), similarity);
		return;
	}
	
}
