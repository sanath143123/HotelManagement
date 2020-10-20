package com.m3bi.hotelbooking.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.model.Room;
import com.m3bi.hotelbooking.repository.RoomRepository;

@Service
public class RoomServiceImpl implements IRoomService{
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RoomRepository roomRepo;
	
	@Override
	public List<Room> getAllRooms()
	{
	     List<Room> roomList = roomRepo.findAll();
	     
	     return roomList;
	}
	
	@Override
	public List<Room> getRoomByHotelId(String id)
	{
		List<Room> room = roomRepo.findByHotelid(Long.valueOf(id));

		return room;
		
	}
	
	@Override
	public List<Room> saveRoom(List<Room> roomList)
	{
		List<Room> response = new ArrayList<>();
		
		roomList.forEach(room -> {
			log.info("hotel id :"+ room.getHotelid());
			roomRepo.save(room);
			room.setStatus("Success");
			response.add(room);
		});
		
		return response;
	}
	
	
	@Override
	public List<Room> updateRoom(List<Room> roomList)
	{
		List<Room> response = new ArrayList<>();
		
		roomList.forEach(room -> {
			Room existingRoom = roomRepo.findByHotelIdRoomType(room.getHotelid(), room.getRoomType());
			
			if(existingRoom.getId() != 0)
			{
				double price = room.getPrice();
				existingRoom.setPrice(price != 0 ? price : existingRoom.getPrice());
				existingRoom.setRoomCount(room.getRoomCount() != 0 ? room.getRoomCount() : existingRoom.getRoomCount());
				roomRepo.save(room);
				room.setStatus("Success");	
			}
			else
			{
				room.setStatus("Error");
				room.setMessage("Room not found");
			}
			
			response.add(room);
		});
		
		return response;
	}
	
	

}
