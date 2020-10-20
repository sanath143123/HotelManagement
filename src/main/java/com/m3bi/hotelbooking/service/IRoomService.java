package com.m3bi.hotelbooking.service;

import java.util.List;

import com.m3bi.hotelbooking.model.Room;

public interface IRoomService {
	
	public List<Room> getAllRooms();
	
	public List<Room> getRoomByHotelId(String id);
	
	public List<Room> saveRoom(List<Room> room);
	
	public List<Room> updateRoom(List<Room> room);
	
	

}
