package com.m3bi.hotelbooking.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.model.Room;
import com.m3bi.hotelbooking.model.RoomBooking;
import com.m3bi.hotelbooking.model.User;
import com.m3bi.hotelbooking.repository.BookingRepository;
import com.m3bi.hotelbooking.repository.RoomRepository;
import com.m3bi.hotelbooking.repository.UserRepository;

@Service
public class RoomBookingService implements IRoomBookingService{

	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	RoomRepository roomRepo;
	
	@Autowired
	UserRepository userRepo;
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	public List<RoomBooking> getAllBookings()
	{
		return bookingRepo.findAll();
	}
	
	@Override
	public List<RoomBooking> createBooking(List<RoomBooking> bookingList)
	{
		List<RoomBooking> response= new ArrayList<>();
		bookingList.forEach(booking -> {
			Optional<Room> room = roomRepo.findById(booking.getRoomid());
			Optional<User> user = userRepo.findById(booking.getUserid());
		    booking.setBookingstatus(getStatus(room.get(), booking.getNoOfRooms(), user.get().getBonus()));
		    booking.setTotalAmount(room.get().getPrice() * booking.getNoOfRooms());
			LocalDateTime bookdate= LocalDateTime.now();
			booking.setBookingDate(bookdate);
			bookingRepo.save(booking);
			updateRoom(room.get(), booking.getNoOfRooms());
			updateUser(user.get(), booking.getTotalAmount());
			    
			booking.setStatus("success");
			response.add(booking);
			
		});
		
		return response;
	}
	
	private String getStatus(Room room, int count, int bonus)
	{
		if(room.getAvailableRooms() >=  count)
		{
			   double price= count * room.getPrice();
			   
			   if((int)price <= bonus)
			   {
				   return "Booked";
			   }
			   else if((int)price > bonus)
				   return "Pending Approval";
		}
		
		return "Rooms Not Available";
		
		
		
	}
	
	private void updateRoom(Room room, int count)
	{
		room.setAvailableRooms((room.getAvailableRooms() - count) > 0 ? room.getAvailableRooms() - count : 0);
		roomRepo.save(room);
	}
	
	private void updateUser(User user, double price)
	{
		user.setBonus((user.getBonus() - (int) price) > 0 ? (user.getBonus() - (int) price) : 0 );
	}
	
	@Override
	public List<RoomBooking> getBookingByHotelid(String id)
	{
		return bookingRepo.findByHotelid(Long.valueOf(id));
		
		
	}
}
