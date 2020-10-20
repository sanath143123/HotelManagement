package com.m3bi.hotelbooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.model.Room;
import com.m3bi.hotelbooking.service.IRoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags="Room")
@RestController
public class RoomController {
	
protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IRoomService roomService;
	
	//@ApiOperation(value = "Get All Rooms", response = Room.class, responseContainer = "List")
	@GetMapping("/rooms")
	public ResponseEntity<?> getRooms() {
		try {

		return new ResponseEntity<List<Room>>(roomService.getAllRooms(), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}

	}

//	@ApiOperation(value = "Create New Rooms", response = Room.class, responseContainer = "List")
	@PostMapping("/rooms")
	public ResponseEntity<?> addRoom(@RequestBody List<Room> room) {
		try {
		log.info("creating new Room : "+room.size());
		return new ResponseEntity<List<Room>>(roomService.saveRoom(room), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
//	@ApiOperation(value = "Update Rooms", response = Room.class, responseContainer = "List")
	@PutMapping("/rooms")
	public ResponseEntity<?> updateRoom(@RequestBody List<Room> room) 
	{
		try {
		log.info("Update room : " + room.size());
		
		return new ResponseEntity<List<Room>>(roomService.saveRoom(room), HttpStatus.CREATED);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
//	@ApiOperation(value = "Get Rooms By Hotel ID", response = Room.class, responseContainer = "List")
	@GetMapping("/rooms/{id}")
	public ResponseEntity<?> getRoomByHotelId(@PathVariable(value = "id") String id) 
	{
		try {
		log.info("Hotel Id : " + id);
		return new ResponseEntity<List<Room>>(roomService.getRoomByHotelId(id), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}


}
