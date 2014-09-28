package com.smartcal.repositories;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smartcal.models.Event;
import com.smartcal.models.User;
import com.smartcal.utils.RepositoryUtils;

@Repository
public class UsersRepositoryJdbcImpl implements UsersRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User getById(int id) {
		String sql = "select * from smartcaldb.users where users.userid = ?;";
		;
		User usr = (User) jdbcTemplate.queryForObject(sql, new Object[] { id },
				new UserMapper());
		return usr;
	}

	@Override
	public void addUser(User usr) {
		String sql = "insert into users (name,email,signupdate,username,password)"
				+ " values (\"?\",\"?\",\"?\",\"?\",\"?\");";
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		jdbcTemplate.update(sql, usr.getName(), usr.getEmail(),
				formatter.format(usr.getSignUpDate()), usr.getUserName(),
				usr.getPassword());
		return;

	}

	@Override
	public void deleteUser(User usr) {
		String sql = "delete from smartcaldb.users where smartcaldb.users.userid = ?;";
		jdbcTemplate.update(sql, usr.getUserId());
		return;
	}

	@Override
	public List<User> getAttendies(Event evt) {
		String sql = "select users.*" 
				+ "from smartcaldb.users, smartcaldb.attending"
				+ "where users.userid = attending.uid and attending.eid = ?;";
		
		return RepositoryUtils.generateUserResultList(jdbcTemplate.queryForList(sql, evt.getEventId()));
	}

}
