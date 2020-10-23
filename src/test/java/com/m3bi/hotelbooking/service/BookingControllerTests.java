package com.m3bi.hotelbooking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.collect.Lists;
import com.m3bi.hotelbooking.model.RoomBooking;

public class BookingControllerTests extends AbstractTest{
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	 
	 @Test
	 public void getAllBookings() throws Exception
	 {
		 String uri="/bookings";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				   
				   int status = mvcResult.getResponse().getStatus();
				   assertEquals(200, status);
				   String content = mvcResult.getResponse().getContentAsString();
			
				 log.info("content"+ content);
	 }
	 
	 @Test
		public void addBooking() throws Exception
		{
		     List<RoomBooking> bookingList = Lists.newArrayList();
			 String uri = "/bookings";
			 RoomBooking booking = new RoomBooking();
			 
			 booking.setHotelid(5300l);
			 booking.setUserid(5001l);
			 booking.setRoomid(5252l);
			 booking.setNoOfRooms(2);
			 
			   bookingList.add(booking);
			   String inputJson = super.mapToJson(bookingList);
			   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			   
			   int status = mvcResult.getResponse().getStatus();
			  // assertEquals(200, status);
			   String content = mvcResult.getResponse().getContentAsString();
			   log.info("content"+ content);
		}
		
		@Test
		public void getBookingByHotel() throws Exception
		{
			String uri="/bookings/5300";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				   
				   int status = mvcResult.getResponse().getStatus();
				   assertEquals(200, status);
				   String content = mvcResult.getResponse().getContentAsString();
			
				   log.info("content"+ content);
		}


}
