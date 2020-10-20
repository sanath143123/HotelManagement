package com.m3bi.hotelbooking.service;

import java.util.List;

import com.m3bi.hotelbooking.model.User;

public interface IUserService {
	
	public List<User> getAllUsers();
	
	public User getUserById(String id);
	
	public List<User> saveUser(List<User> user);
	
	public List<User> updateUser(List<User> user);

}
