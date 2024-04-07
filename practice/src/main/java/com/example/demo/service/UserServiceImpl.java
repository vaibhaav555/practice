package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import net.bytebuddy.implementation.bytecode.Throw;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepository userRepo;
	
	@Override
	public void addNewUser(User user) {
	
		this.userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		  List<User> getAll = this.userRepo.findAll();
		return getAll;
	}

	@Override
	public User updateUser(Long id,User user) {
//	Optional<User> gid = this.userRepo.findById(id);
//		     User existing_user = gid.get();
//		      existing_user.setName(user.getName());
//		      existing_user.setProfession(user.getProfession());
//		      existing_user.setCity(user.getCity());
//		      User saved_user = this.userRepo.save(existing_user);
//		      return saved_user;
	/*
	 * if(gid.isPresent()) { User existing_user = gid.get();
	 * existing_user.setName(user.getName());
	 * existing_user.setProfession(user.getProfession());
	 * existing_user.setCity(user.getCity()); User saved_user =
	 * this.userRepo.save(existing_user); return saved_user; }else { throw new
	 * NoSuchElementException("User not found with ID: " + id); }
	 */
	     List<User> all = this.userRepo.findAll();
         List<User> updateduser = all.stream().map(e ->{
                        	 if(e.getId().equals(id)) {
                        		 e.setName(user.getName());
                        		 e.setProfession(user.getProfession());
                        		 e.setCity(user.getCity());
                        	 }
                        	 return e;
                         }).collect(Collectors.toList());
             User currentuser = updateduser.stream().filter(e->e.getId()==id).findFirst().get();
             this.userRepo.save(currentuser);
             return currentuser;
                         /*
						 * Copyright 2024 the original author or authors.
						 *
						 * Licensed under the Apache License, Version 2.0 (the "License");
						 * you may not use this file except in compliance with the License.
						 * You may obtain a copy of the License at
						 *
						 *      https://www.apache.org/licenses/LICENSE-2.0
						 *
						 * Unless required by applicable law or agreed to in writing, software
						 * distributed under the License is distributed on an "AS IS" BASIS,
						 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
						 * See the License for the specific language governing permissions and
						 * limitations under the License.
						 */

//		  List<User> updatedUsers = allUsers.stream()
//		            .map(existingUser -> {
//		                if (existingUser.getId().equals(id)) {
//		                    existingUser.setName(user.getName());
//		                    existingUser.setProfession(user.getProfession());
//		                    existingUser.setCity(user.getCity());
//		                }
//		                return existingUser;
//		            })
//		            .collect(Collectors.toList());
	        
	}

	@Override
	public User patchUser(Long id, Map<String, Object> data) {
		   Optional<User> existing_user = this.userRepo.findById(id);
		   System.out.println("exists"+existing_user.get());
	
	        if(existing_user.isPresent()) {
	        	
	        	data.forEach((key,value)->{
	        		Field getField= ReflectionUtils.findField(User.class, key);
	        		 getField.setAccessible(true);
	        		 ReflectionUtils.setField(getField, existing_user.get(), value);
	        	});
	        	
	        	return this.userRepo.save(existing_user.get());
	        }
			return null;
	}

	@Override
	public void deleteUser(Long id) {
	this.userRepo.deleteById(id);
		
	}

	@Override
	public void addListUsers(List<User> users) {
	   userRepo.saveAll(users);
		
	}

	@Override
	public User getByName(String name) {
		User byName = this.userRepo.getUserByName(name);
		return byName;
	}

	@Override
	public User getByCity(String city) {
		
		return this.userRepo.findByCity(city);
	}
	
	

	
	
	

}
