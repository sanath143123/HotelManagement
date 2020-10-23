package com.m3bi.hotelbooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.model.Hotel;
import com.m3bi.hotelbooking.repository.HotelRepository;

@Service
public class HotelServiceImpl implements IHotelService{
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	HotelRepository hotelRepo;
	
	@Override
	public List<Hotel> getAllHotels()
	{
		List<Hotel> hotelList = hotelRepo.findAll();
		
		return hotelList;
	}
	
	@Override
	public Hotel getHotelById(String id)
	{
		log.info("id is ::" + id );

		Optional<Hotel> hotel = hotelRepo.findById(Long.valueOf(id));
		
		return hotel.get();
	}
	
	@Override
	public List<Hotel> saveHotel(List<Hotel> hotelList)
	{
		List<Hotel> response =  new ArrayList<>();
		hotelList.forEach(hotel -> {
			int count= hotelRepo.countFindByHotel(hotel.getHotelName(), hotel.getPlace());
			if(count == 0)
			{
			hotelRepo.save(hotel);
			hotel.setStatus("Success");
			response.add(hotel);
			}
			else
			{
				hotel.setStatus("Error");
				hotel.setMessage("Hotel with name and place already exists");
				response.add(hotel);
			}
		});
		
		return response;
	}
	

}
