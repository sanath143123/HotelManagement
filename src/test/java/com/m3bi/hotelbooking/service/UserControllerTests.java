package com.m3bi.hotelbooking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.collect.Lists;
import com.m3bi.hotelbooking.model.User;

public class UserControllerTests extends AbstractTest {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getUser() throws Exception {
		String uri = "/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] userlist = super.mapFromJson(content, User[].class);
		assertTrue(userlist.length > 0);
	}

	@Test
	public void addUser() throws Exception {
		String uri = "/users";
		List<User> userList = Lists.newArrayList();

		User user = new User();
		user.setName("Rajesh");
		user.setBonus(2000);

		userList.add(user);
		String inputJson = super.mapToJson(userList);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		log.info("content" + content);
	}

	

	@Test
	public void getUserById() throws Exception {
		String uri = "/users/5100";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		log.info("content" + content);
	}

}
