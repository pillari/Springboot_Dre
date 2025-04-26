package org.jsp.dre.controller;

import org.jsp.dre.entity.User;
import org.jsp.dre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/users")
public class UserController {
	
	
	
	@Autowired
	private UserService service;
	
	
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user){
		
		
		return service.saveUser(user);
	}
	
	@GetMapping
	
	public ResponseEntity<?> findAll(){
		
		
		return service.findAll();
	}
	
	
	
	
	@GetMapping("id/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id){
		
		
		return service.findUserById(id);
	}

	
	@DeleteMapping("id/{id}")
public ResponseEntity<?> deleteUserById(@PathVariable int id){
		
		
		return service.deleteUserById(id);
	}

	@PatchMapping("id/{id}")
	
public ResponseEntity<?> updateUserById(@PathVariable int id,@RequestBody User user){
		
		
		return service.updateUserById(id,user);
	}
	
	
	@GetMapping("/match/{id}/{top}")
	public ResponseEntity<?> findMatch(@PathVariable int id,@PathVariable int top){
		
		
		return service.findMatch(id,top);
	}

	

	
	
}
