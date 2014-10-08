package com.smartcal.repositories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smartcal.models.User;
import com.smartcal.utils.RepositoryUtils;
import com.smartcal.utils.SQLQueries;

@Repository
public class UsersRepositoryJdbcImpl implements UsersRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User getById(int id) {
		User usr = (User) jdbcTemplate.queryForObject(
				SQLQueries.SQL_GET_USER_BY_ID, new Object[] { id },
				new UserMapper());
		return usr;
	}

	@Override
	public List<User> getUsers() {
		return RepositoryUtils.generateUserResultList(jdbcTemplate
				.queryForList(SQLQueries.SQL_GET_ALL_USERS));
	}

	@Override
	public void addUser(User usr) {
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		jdbcTemplate.update(SQLQueries.SQL_ADD_USER, usr.getName(),
				usr.getEmail(), formatter.format(usr.getSignUpDate()),
				usr.getUserName(), usr.getPassword());
		return;

	}

	@Override
	public void deleteUser(int usrId) {
		jdbcTemplate.update(SQLQueries.SQL_DELETE_USER, usrId);
		return;
	}

	@Override
	public List<User> getAttendies(int evtId) {
		return RepositoryUtils.generateUserResultList(jdbcTemplate
				.queryForList(SQLQueries.SQL_GET_ATTENDIES, evtId));
	}

}
