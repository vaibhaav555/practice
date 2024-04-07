package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	//Normal SQL query
//    @Query(value="Select * from User where name=:name ",nativeQuery = true)
//	public User getUserByName(@Param("name")String name);
	
	//Java Persistence Query LAnguage(JPQL)
	@Query("Select u From User u where u.name =:name ")
	public User getUserByName(@Param("name")String name);
	
	public User findByCity(String city);
}
