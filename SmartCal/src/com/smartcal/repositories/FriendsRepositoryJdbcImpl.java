package com.smartcal.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smartcal.models.User;
import com.smartcal.utils.RepositoryUtils;

public class FriendsRepositoryJdbcImpl implements FriendsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> getFriendsFor(int usr) {
		String sql = "select u.*"
				+ "from smartcaldb.friend as f, smartcaldb.users as u"
				+ "where f.user1 = ? and f.user2 = u.userid";
		
		return RepositoryUtils.generateUserResultList(jdbcTemplate.queryForList(sql,usr));
	}

	@Override
	public List<User> getFriendsWithSimilarityAbove(int usrId,
			double minSimilarity) {
		
		String sql = "select u.*"
				+ "from smartcaldb.friend as f, smartcaldb.users as u"
				+ "where f.user1 = ? and f.similarity > ? and f.user2 = u.userid";
		
		return RepositoryUtils.generateUserResultList(jdbcTemplate.queryForList(sql,usrId,minSimilarity));
	}

	@Override
	public List<User> getFriendsAttending(int usr, int evt) {
		String sql = "select u.*"
		+ "from smartcaldb.events as e, smartcaldb.attending as a,"
		+ " smartcaldb.friend as f, smartcaldb.users as u"
		+ "where e.eventid = ? and f.user1 = ? and a.uid = f.user2 and a.eid = ? and a.uid = u.userid";
		
		return RepositoryUtils.generateUserResultList(jdbcTemplate.queryForList(sql,
				evt, usr, evt));
	}
	
	@Override
	public void createFriendship(int usr1, int usr2, double similarity) {
		String sql = "insert into smartcaldb.friend (user1, user2, similarity) values (?,?,?);";
		jdbcTemplate.update(sql, usr1, usr2, similarity);
		return;
	}
	
}
