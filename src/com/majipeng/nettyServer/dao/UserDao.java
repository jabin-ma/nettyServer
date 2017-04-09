package com.majipeng.nettyServer.dao;

import majipeng.model.User;

public interface UserDao {

	public boolean add(User user);
	
	public void update(User user);
	
	public void delete(int id);
	
	public void findById(int id);
	
	public void logon(User user);
}
