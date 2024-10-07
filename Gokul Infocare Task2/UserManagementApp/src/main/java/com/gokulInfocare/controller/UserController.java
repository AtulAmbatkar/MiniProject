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
import com.gokulInfocare.dto.UserResponse;
import com.gokulInfocare.model.User;
import com.gokulInfocare.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserDetailsRequest userDetailsRequest) {
		if (userDetailsRequest == null) {
			return new ResponseEntity<>(new UserResponse("For Add User Must Be Required "), HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		BeanUtils.copyProperties(userDetailsRequest, user);
		String addUserResponse = userService.addUser(user);
		return new ResponseEntity<>(new UserResponse(addUserResponse), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		if (id == 0) {
			return new ResponseEntity<>(new UserResponse("For Deletion Id Must be required"), HttpStatus.BAD_REQUEST);
		}
		User user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> fetchAllUsers() {
		List<User> userList = userService.fetchAllUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		if (user == null) {
			return new ResponseEntity<>(new UserResponse("For Updation User Must Be Required "),
					HttpStatus.BAD_REQUEST);
		}
		String result = userService.updateUser(user.getId(), user);
		return new ResponseEntity<>(new UserResponse(result), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		if (id == 0) {
			return new ResponseEntity<>(new UserResponse("For Deletion Id Must be required"), HttpStatus.BAD_REQUEST);
		}
		String deleteUserResponse = userService.deleteUserById(id);
		return new ResponseEntity<>(new UserResponse(deleteUserResponse), HttpStatus.OK);
	}
}
