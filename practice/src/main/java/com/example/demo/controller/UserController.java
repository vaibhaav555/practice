package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
//This is the normal rest controller
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userservice;

  // @PostMapping("/create")
  // public String addUser(@RequestBody User user) {
  // this.userservice.addNewUser(user);
  // return "added user successfully";
  // }
  @PostMapping("/create")
  public ResponseEntity<String> addUser(@RequestBody User user) {
    this.userservice.addNewUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body("user Added Successfully");
  }

  @PostMapping("/createListEntry")
  public ResponseEntity<String> addListUsers(@RequestBody List<User> users) {
    this.userservice.addListUsers(users);
    return ResponseEntity.status(HttpStatus.CREATED).body("users Added Successfully");
  }
  //
  // @GetMapping("/get")
  // public List<User> getAllUser(){
  // List<User> allUsers = this.userservice.getAllUsers();
  // return allUsers;
  // }

  @GetMapping("/get")
  public ResponseEntity<List<User>> getAllUser() {
    List<User> allUsers = this.userservice.getAllUsers();
    return ResponseEntity.ok(allUsers);
  }

  @GetMapping("/getByName/{name}")
  public ResponseEntity<User> getByName(@PathVariable() String name) {
    User current_user = this.userservice.getByName(name);
    return ResponseEntity.ok(current_user);
  }

  @GetMapping("/getByCity/{c}")
  public User getByCity(@PathVariable("c") String city) {
    User current_user = this.userservice.getByCity(city);
    return current_user;
  }

  @PutMapping("/update/{id}")
  public User updateuser(@PathVariable("id") Long id, @RequestBody User user) {
    System.out.println(id);
    return this.userservice.updateUser(id, user);
  }

  @PatchMapping("/patch/{id}")
  public User patchuser(@PathVariable("id") Long id, @RequestBody Map<String, Object> data) {
    System.out.println("yes i m here" + data);
    return this.userservice.patchUser(id, data);

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
    this.userservice.deleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully");
  }

}
