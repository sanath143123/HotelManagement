package com.m3bi.hotelbooking.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.model.User;
import com.m3bi.hotelbooking.repository.UserRepository;



@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<User> getAllUsers()
	{
		List<User> userList =userRepo.findAll();
		
		return userList;
	}
	
	@Override
	public List<User> saveUser(List<User> userList)
	{
		List<User> response = new ArrayList<>();
		userList.forEach(user -> {
			
		userRepo.save(user);
		user.setStatus("Success");
		response.add(user);
		});
		
		return response;
		
	}
	
	
	
	@Override
	public List<User> updateUser(List<User> userList)
	{
		List<User> response = new ArrayList<>();
		userList.forEach(user -> {
			User existingUser = userRepo.findByName(user.getName());
			if(existingUser.getId() != 0)
			{
			existingUser.setBonus(user.getBonus());
			userRepo.save(existingUser);
			user.setStatus("Success");
			}
			else
			{
				user.setStatus("Error");
				user.setMessage("User not found");
			}
		response.add(user);
		});
		
		return response;
		
	}
	
	
	
	@Override
	public User getUserById(String id)
	{
		Optional<User> user =userRepo.findById(Long.valueOf(id));
		
		return user.get();
	}

}
