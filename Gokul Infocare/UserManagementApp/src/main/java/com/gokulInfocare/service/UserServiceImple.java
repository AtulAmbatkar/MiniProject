package com.gokulInfocare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokulInfocare.exception.UserNotFoundException;
import com.gokulInfocare.model.User;
import com.gokulInfocare.repository.UserRepository;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String addUser(User user) {
		Optional<User> optUser = userRepository.findUserByEmail(user.getEmail());
		if (optUser.isEmpty()) {
			User savedUser = userRepository.save(user);
			return "User added successfully with id " + savedUser.getId();
		} else {
			User existingUser = optUser.get();
			return "User already added with id " + existingUser.getId() + ". Email must be unique.";
		}
	}

	@Override
	public User getUser(int id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + id));
	}

	@Override
	public List<User> fetchAllUser() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public String updateUser(int id, User user) {
		Optional<User> userFind = userRepository.findById(id);
		if (userFind.isPresent()) {
			User user1 = userFind.get();
			if(!user.equals(user1)) {
				return "Email Duplicate, Email must be unique " + user1.getEmail() + " with ID " + user1.getEmail();
			}
			BeanUtils.copyProperties(user, user1);
			
			 userRepository.save(user);
			return "user updated successfuly with id: " + id + " and user " + user1;
		}
		return "for Updation user not found with id : " + id;
	}



	@Override
	public String deleteUserById(int id) {
		Optional<User> userFind = userRepository.findById(id);
		if (userFind.isPresent()) {
			userRepository.deleteById(userFind.get().getId());
			return "User deleted successfull ";
		}
		return "User not found for deleteion with id " + id;
	}

}
