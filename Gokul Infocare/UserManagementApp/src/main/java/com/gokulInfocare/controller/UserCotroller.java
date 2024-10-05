package com.gokulInfocare.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokulInfocare.dto.UserDetailsRequest;
import com.gokulInfocare.dto.UserDetailsResponse;
import com.gokulInfocare.dto.UserResponse;
import com.gokulInfocare.model.User;
import com.gokulInfocare.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserCotroller {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUer")
	public ResponseEntity<?> addUser(@RequestBody UserDetailsRequest userDetailsRequest){
		User user = new User();
		BeanUtils.copyProperties(userDetailsRequest, user);
		String addUSer= userService.addUser(user);
		System.out.println(addUSer);
		return new ResponseEntity<>(new UserResponse(addUSer), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable int id){
		User user = userService.getUser(id);
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> FetchAllUsers(){
		List<User> userList = userService.fetchAllUser();
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@PutMapping("/updateUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> updateUser(@RequestBody UserDetailsResponse userDetailsResponse){
		User user = new User();
		BeanUtils.copyProperties(userDetailsResponse, user);
		String result = userService.updateUser(user.getId(), user);
		return new ResponseEntity<>(new UserResponse(result),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		String deleteUser = userService.deleteUserById(id);
		return new ResponseEntity<>(new UserResponse(deleteUser),HttpStatus.OK);
	}

}
