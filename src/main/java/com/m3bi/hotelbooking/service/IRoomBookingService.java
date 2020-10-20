package com.m3bi.hotelbooking.service;

import java.util.List;

import com.m3bi.hotelbooking.model.RoomBooking;

public interface IRoomBookingService {
	
	public List<RoomBooking> createBooking(List<RoomBooking> bookingList);
	
	public List<RoomBooking> getBookingByHotelid(String id);
	
	public List<RoomBooking> getAllBookings();

}
