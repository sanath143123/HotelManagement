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
import com.m3bi.hotelbooking.model.Hotel;
import com.m3bi.hotelbooking.model.User;

public class HotelControllerTests extends AbstractTest{
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	 @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	 
	 @Test
	 public void getHotels() throws Exception
	 {
		 String uri="/hotels";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				   
				   int status = mvcResult.getResponse().getStatus();
				   assertEquals(200, status);
				   String content = mvcResult.getResponse().getContentAsString();
				
				 log.info("content"+ content);
	 }
	 
	 @Test
		public void addHotel() throws Exception
		{
		 List<Hotel> hotelList = Lists.newArrayList();
			 String uri = "/hotels";
			   Hotel hotel = new Hotel();
			   hotel.setHotelName("Novetal");
			   hotel.setPlace("Hyderabad");
			   
			   hotelList.add(hotel);
			   String inputJson = super.mapToJson(hotelList);
			   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			   
			   int status = mvcResult.getResponse().getStatus();
			   assertEquals(200, status);
			   String content = mvcResult.getResponse().getContentAsString();
			   log.info("content"+ content);
		}
	 
	 @Test
		public void updateHotel() throws Exception
		{
		  List<Hotel> hotelList = Lists.newArrayList();
			 String uri = "/hotels";
			   Hotel hotel = new Hotel();
			   hotel.setHotelName("Novetal");
			   hotel.setPlace("Hyderabad");
			   
			   hotelList.add(hotel);
			   String inputJson = super.mapToJson(hotelList);
			   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			   
			   int status = mvcResult.getResponse().getStatus();
			   assertEquals(201, status);
			   String content = mvcResult.getResponse().getContentAsString();
			   log.info("content"+ content);
		}
		
		@Test
		public void getHotelById() throws Exception
		{
			String uri="/hotels/6600";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				   
				   int status = mvcResult.getResponse().getStatus();
				   assertEquals(200, status);
				   String content = mvcResult.getResponse().getContentAsString();
				Hotel hotel = super.mapFromJson(content, Hotel.class);
				   assertTrue(hotel.getHotelName() != null ? true : false);
		}

}
