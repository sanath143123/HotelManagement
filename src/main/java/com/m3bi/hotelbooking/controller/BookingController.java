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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.model.RoomBooking;
import com.m3bi.hotelbooking.service.IRoomBookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags="Bookings")
@RestController
public class BookingController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IRoomBookingService bookingService;

	// @ApiOperation(value = "Get All Bookings", response = RoomBooking.class,
	// responseContainer = "List")
	@GetMapping("/bookings")
	public ResponseEntity<?> getAllBookings() {
		try {
			return new ResponseEntity<List<RoomBooking>>(bookingService.getAllBookings(), HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

//	@ApiOperation(value = "Add New Booking", response = RoomBooking.class, responseContainer = "List")
	@PostMapping("/bookings")
	public ResponseEntity<?> addBooking(@RequestBody List<RoomBooking> room) {
		log.info("creating new Room : " + room.size());
		try {
			return new ResponseEntity<List<RoomBooking>>(bookingService.createBooking(room), HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

//	@ApiOperation(value = "Get All Booking by Hotel ID", response = RoomBooking.class, responseContainer = "List")
	@GetMapping("/bookings/{id}")
	public ResponseEntity<?> getBookingByHotel(@PathVariable(value = "id") String id) {
		try {
			return new ResponseEntity<List<RoomBooking>>(bookingService.getBookingByHotelid(id), HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
