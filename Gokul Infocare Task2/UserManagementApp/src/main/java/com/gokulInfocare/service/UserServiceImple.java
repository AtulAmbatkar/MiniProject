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

		if (user.getFirstName() == null || user.getFirstName().isBlank()) {
			return "First name is required";
		}

		if (user.getLastName() == null || user.getLastName().isBlank()) {
			return "Last name is required";
		}

		
		if (user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()) {
			return "Phone number is required";
		}
		
		String PHONE_PATTERN = "^[0-9]{10}$"; 
	    if (!user.getPhoneNumber().matches(PHONE_PATTERN)) {
	    	return "Phone number format is invalid (it should be exactly 10 digits)";
	    }

		if (user.getEmail() == null || user.getEmail().isBlank()) {
			return "Email is required";
		}
		
	    String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
	    if (!user.getEmail().matches(EMAIL_PATTERN)) {
	        return "Email format is invalid (examle@gmail.com)";
	    }

		if (user.getAddress() == null || user.getAddress().isBlank()) {
			return "Address is required";
		}

		Optional<User> optUser = userRepository.findUserByEmail(user.getEmail());
		if (optUser.isEmpty()) {

			User savedUser = userRepository.save(user);
			return "User added successfully with id " + savedUser.getId();
		} else {

			User existingUser = optUser.get();
			return "User already exists with id " + existingUser.getId() + ". Email must be unique.";
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
	    if (!userFind.isPresent()) {
	        return "For updation, user not found with id: " + id;
	    }
	    
	    User existingUser = userFind.get();
	    
	    if(user.equals(existingUser)) {
	    	if (user.getFirstName() == null || user.getFirstName().isBlank()) {
				return "First name is required";
			}

			if (user.getLastName() == null || user.getLastName().isBlank()) {
				return "Last name is required";
			}

			if (user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()) {
				return "Phone number is required";
			}
			
			String PHONE_PATTERN = "^[0-9]{10}$"; 
		    if (!user.getPhoneNumber().matches(PHONE_PATTERN)) {
		    	return "Phone number format is invalid (it should be exactly 10 digits)";
		    }

			if (user.getAddress() == null || user.getAddress().isBlank()) {
				return "Address is required";
			}
			
		    BeanUtils.copyProperties(user, existingUser);
		    
		    userRepository.save(existingUser);
		    
		    return "User updated successfully with id: " + existingUser.getId() + " and user: " + existingUser;
	    }
	    return "Email must be unique: " + existingUser.getEmail() + " already exists with ID: " + existingUser.getId();
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
