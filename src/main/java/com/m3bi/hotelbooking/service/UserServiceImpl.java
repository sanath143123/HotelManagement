package com.m3bi.hotelbooking.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.model.User;
import com.m3bi.hotelbooking.model.RoomBooking;
import com.m3bi.hotelbooking.repository.BookingRepository;
import com.m3bi.hotelbooking.repository.UserRepository;



@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	IRoomBookingService bookingService;
	
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
			int count = userRepo.countFindByName(user.getName());
			if(count == 0)
			{
				userRepo.save(user);
				user.setStatus("Success");
				response.add(user);
			}
			else
			{
				user.setStatus("Error");
				user.setMessage("User already present");
				response.add(user);
			}
			
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
				if(user.getBonus() !=0)
				{
			existingUser.setBonus(existingUser.getBonus() + user.getBonus());
			userRepo.save(existingUser);
			
			bookingService.updateBookingForUser(existingUser);
			 //List<RoomBooking> roomBooking = bookingRepo.findByUserid(user.getId(), "Pending_Approval");
			 
				}
			existingUser.setStatus("Success");
			response.add(existingUser);
			}
			else
			{
				user.setStatus("Error");
				user.setMessage("User not found");
				response.add(user);
			}
		
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
