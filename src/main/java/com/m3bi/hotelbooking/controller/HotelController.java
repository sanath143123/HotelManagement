package com.m3bi.hotelbooking.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.m3bi.hotelbooking.model.Hotel;
import com.m3bi.hotelbooking.service.IHotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags="Hotel")
@RestController
public class HotelController {
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IHotelService hotelService;
	
//	@ApiOperation(value = "Get All Hotels", response = Hotel.class, responseContainer = "List")
	@GetMapping("/hotels")
	public ResponseEntity<?> getHotels() {
          try {
		return new ResponseEntity<List<Hotel>>(hotelService.getAllHotels(), HttpStatus.OK);
          }
          catch(Exception ex)
          {
        	  return ResponseEntity.badRequest().build();
          }

	}

//	@ApiOperation(value = "Add New Hotel", response = Hotel.class, responseContainer = "List")
	@PostMapping("/hotels")
	public ResponseEntity<?> addHotel(@RequestBody List<Hotel> hotel) {
		try {
		log.info("creating new Hotel : "+hotel.size());
		return new ResponseEntity<List<Hotel>>(hotelService.saveHotel(hotel), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
//	@ApiOperation(value = "Update Hotel Info", response = Hotel.class, responseContainer = "List")
	@PutMapping("/hotels")
	public ResponseEntity<?> updateHotel(@RequestBody List<Hotel> hotel) 
	{
		try {
		log.info("Update Hotel : " + hotel.size());
		
		return new ResponseEntity<List<Hotel>>(hotelService.saveHotel(hotel), HttpStatus.CREATED);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
//	@ApiOperation(value = "Get Hotel By Id", response = Hotel.class)
	@GetMapping("/hotels/{id}")
	public ResponseEntity<?> getHotelById(@PathVariable(value = "id") String id) 
	{
		try
		{
		log.info("Hotel Id : " + id);
		return new ResponseEntity<Hotel>(hotelService.getHotelById(id), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}


}
