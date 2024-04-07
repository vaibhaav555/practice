package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.User;

public interface UserService {
  
	void addNewUser(User user);
	List<User> getAllUsers();
	User updateUser(Long id,User user);
    User patchUser(Long id,Map<String,Object> data);
    void deleteUser(Long id);
    void addListUsers(List<User> users);
    User getByName(String name);
	User getByCity(String city);
}
