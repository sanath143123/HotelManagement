package com.m3bi.hotelbooking.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.model.User;
import com.m3bi.hotelbooking.service.IUserService;

import io.swagger.annotations.ApiOperation;



@RestController
public class UserController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userService;

    @ApiOperation(value = "Get All Users", response = User.class)	
	@GetMapping("/users")
	public ResponseEntity<?> getUser() {
		try {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}

	}

    @ApiOperation(value = "Create User", response = User.class)
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody List<User> user) {
		try {
		log.info("creating new users : "+user.size());
		return new ResponseEntity<List<User>>(userService.saveUser(user), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
   @ApiOperation(value = "Update User", response = User.class)
	@PutMapping("/users")
	public ResponseEntity<?> updateUser(@RequestBody List<User> user) 
	{
		try {
		log.info("Update users : " + user.size());
		
		return new ResponseEntity<List<User>>(userService.updateUser(user), HttpStatus.CREATED);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
 //   @ApiOperation(value = "Get User by  Id", response = User.class)
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") String id) 
	{
		try {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
    
    

}
