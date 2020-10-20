package com.m3bi.hotelbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.m3bi.hotelbooking.model.Room;
		

public interface RoomRepository extends JpaRepository<Room	, Long>{
	
	public List<Room> findByHotelid(Long id);
	
	@Query(value="select * from hotelschma.rooms r where r.hotelid=?1 and r.roomtype= ?2", nativeQuery = true)
	public Room findByHotelIdRoomType(Long hotelid, String roomtype);

}