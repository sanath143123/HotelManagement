package com.m3bi.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m3bi.hotelbooking.model.Hotel;
import com.m3bi.hotelbooking.model.Room;


public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	

}
