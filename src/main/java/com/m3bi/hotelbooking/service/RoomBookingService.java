package com.m3bi.hotelbooking.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
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
			updateRoom(room.get(), booking.getNoOfRooms(),"");
			if(booking.getBookingstatus().equals("Booked"))
			updateUser(user.get(), booking.getTotalAmount());
			    
			booking.setStatus("success");
			response.add(booking);
			
		});
		
		return response;
	}
	
	private String getStatus(Room room, int count, int bonus)
	{
		//String status="";
		double price= count * room.getPrice();
		List<String> flag=Lists.newArrayList();
		if((int)price <= bonus)
		{
				if(room.getAvailableRooms() >=  count)
				{
					return "Booked";
					   
				}
				else
				{
					List<RoomBooking> bookingList = bookingRepo.findByRoomid(room.getId(), "Pending_Approval");
					if(bookingList.size() == 0)
						return "Pending_Approval";
					else
					{
						
					flag= bookingList.stream().map((booking) -> {
							Optional<Room> ExistingRoom = roomRepo.findById(room.getId());
						
								if(ExistingRoom.get().getAvailableRooms() <= count)
								{
									booking.setBookingstatus("Cancelled");
									bookingRepo.save(booking);
									updateRoom(ExistingRoom.get(),booking.getNoOfRooms(),"Add");
									
								}
								return "Booked";
												
						}).collect(Collectors.toList());
						
						
					}
				}
		}
		
		return flag.size() > 0 ? "Booked" : "Pending_Approval";
		
		
		
		
		
	
		
	}
	
	private void updateRoom(Room room, int count, String action)
	{
		if(!action.equals("Add"))
		room.setAvailableRooms((room.getAvailableRooms() - count) > 0 ? room.getAvailableRooms() - count : 0);
		else
		room.setAvailableRooms(room.getAvailableRooms() + count);	
		roomRepo.save(room);
	}
	
	
	  private void updateUser(User user, double price) 
	  {
	  user.setBonus((user.getBonus() - (int) price) > 0 ? (user.getBonus() - (int) price) : 0 ); 
	  userRepo.save(user);
	  }
	 
	
	@Override
	public List<RoomBooking> getBookingByHotelid(String id)
	{
		return bookingRepo.findByHotelid(Long.valueOf(id));
		
		
	}
	
	public void updateBookingForUser(User user)
	{
		List<RoomBooking> roomBooking = bookingRepo.findByUserid(user.getId(), "Pending_Approval");
		
		roomBooking.forEach(booking -> {
			
			if(booking.getTotalAmount() <= user.getBonus())
			{
				booking.setBookingstatus("Booked");
				bookingRepo.save(booking);
				updateUser(user, booking.getTotalAmount());
			}
			
		});
		
		
	}
	
}
