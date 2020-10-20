package com.m3bi.hotelbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.m3bi.hotelbooking.model.RoomBooking;



public interface BookingRepository extends JpaRepository<RoomBooking, Long>{

	public List<RoomBooking> findByHotelid(Long id);
	
}
