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
import com.m3bi.hotelbooking.model.Room;


public class RoomControllerTest extends AbstractTest{
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	 
	 @Test
	 public void getRooms() throws Exception
	 {
		 String uri="/rooms";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				   
				   int status = mvcResult.getResponse().getStatus();
				   assertEquals(200, status);
				   String content = mvcResult.getResponse().getContentAsString();
				Room[] roomlist = super.mapFromJson(content, Room[].class);
				   assertTrue(roomlist.length > 0);
	 }
	 
	 @Test
		public void addRoom() throws Exception
		{
		 List<Room> roomList = Lists.newArrayList();
			 String uri = "/rooms";
			  Room room = new Room();
			  room.setRoomType("Double Bed");
			  room.setPrice(600);
			  room.setRoomCount(7);
			   roomList.add(room);
			   String inputJson = super.mapToJson(roomList);
			   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			   
			   int status = mvcResult.getResponse().getStatus();
			   assertEquals(200, status);
			   String content = mvcResult.getResponse().getContentAsString();
			   log.info("content"+ content);
		}
	 
	 @Test
		public void updateRoom() throws Exception
		{
		 List<Room> roomList = Lists.newArrayList();
			 String uri = "/rooms";
			  Room room = new Room();
			  room.setRoomType("Double Bed");
			  room.setPrice(600);
			  room.setRoomCount(7);
			   
			  roomList.add(room);
			   String inputJson = super.mapToJson(roomList);
			   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			   
			   int status = mvcResult.getResponse().getStatus();
			   assertEquals(201, status);
			   String content = mvcResult.getResponse().getContentAsString();
			   log.info("content"+ content);
		}
		
		@Test
		public void getRoomByHotelId() throws Exception
		{
			String uri="/rooms/5300";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				   
				   int status = mvcResult.getResponse().getStatus();
				   assertEquals(200, status);
				   String content = mvcResult.getResponse().getContentAsString();
				Room[] room = super.mapFromJson(content, Room[].class);
				   assertTrue(room.length != 0 ? true : false);
		}


}
