package com.smartcal.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smartcal.models.User;
import com.smartcal.utils.RepositoryUtils;
import com.smartcal.utils.SQLQueries;

@Repository
public class FriendsRepositoryJdbcImpl implements FriendsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> getFriendsFor(int usr) {
		return RepositoryUtils.generateUserResultList(jdbcTemplate
				.queryForList(SQLQueries.SQL_GET_FRIENDS_FOR_USER, usr));
	}

	@Override
	public List<User> getFriendsWithSimilarityAbove(int usrId,
			double minSimilarity) {
		return RepositoryUtils.generateUserResultList(jdbcTemplate
				.queryForList(SQLQueries.SQL_GET_FRIENDS_WITH_SIMILARITY,
						usrId, minSimilarity));
	}

	@Override
	public List<User> getFriendsAttending(int usr, int evt) {
		return RepositoryUtils.generateUserResultList(jdbcTemplate
				.queryForList(SQLQueries.SQL_GET_FRIENDS_ATTENDING, evt, usr,
						evt));
	}

	@Override
	public void createFriendship(int usr1, int usr2, double similarity) {
		jdbcTemplate.update(SQLQueries.SQL_CREATE_FRIENDSHIP, usr1, usr2, similarity);
		return;
	}

}
