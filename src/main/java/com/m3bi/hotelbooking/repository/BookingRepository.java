package com.m3bi.hotelbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.m3bi.hotelbooking.model.RoomBooking;



public interface BookingRepository extends JpaRepository<RoomBooking, Long>{

	public List<RoomBooking> findByHotelid(Long id);
	
	@Query(value="select * from hotelschma.roombooking r where r.userid=?1 and r.status= ?2", nativeQuery = true)
	public List<RoomBooking> findByUserid(Long id,String status);
	
	@Query(value="select * from hotelschma.roombooking r where r.roomid=?1 and r.status= ?2", nativeQuery = true)
	public List<RoomBooking> findByRoomid(Long id,String status);
	
}
