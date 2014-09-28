package com.smartcal.repositories;

import java.util.List;

import com.smartcal.models.User;

public interface UsersRepository {
	
	public void addUser(User usr);
	
	public void deleteUser(int usr);
	
	public User getById(int id);
	
	public List<User> getUsers();
	
	public List<User> getAttendies(int evtId);
	
}
