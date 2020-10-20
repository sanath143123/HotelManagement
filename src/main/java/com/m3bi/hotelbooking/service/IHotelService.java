package com.m3bi.hotelbooking.service;

import java.util.List;

import com.m3bi.hotelbooking.model.Hotel;

public interface IHotelService {
	
	public List<Hotel> getAllHotels();
	
	public List<Hotel> saveHotel(List<Hotel> hotel);
	
	public Hotel getHotelById(String id);

}
