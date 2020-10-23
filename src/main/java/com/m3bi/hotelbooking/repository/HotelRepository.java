package com.m3bi.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.m3bi.hotelbooking.model.Hotel;



public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	@Query(value="select count(*) from hotelschma.hotels r where r.hotelname=?1 and r.place= ?2", nativeQuery = true)
	public int countFindByHotel(String hotelname, String place);	

}
