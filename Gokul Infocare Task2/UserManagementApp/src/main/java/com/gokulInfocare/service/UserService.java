package com.gokulInfocare.service;

import java.util.List;

import com.gokulInfocare.model.User;

public interface UserService {
	
	// add , updata , getall , get , delete
	
	public String addUser(User user);
	
	public User getUser(int id);
	
	public List<User> fetchAllUser();
	
	public String updateUser(int id, User user);
	
	public String deleteUserById(int id);

}
