package org.jsp.dre.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jsp.dre.dao.UserDao;
import org.jsp.dre.dto.MatchingUser;
import org.jsp.dre.dto.util.SortByAgeDiff;
import org.jsp.dre.entity.User;
import org.jsp.dre.responsestructure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	
	private UserDao dao;


	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		
		
		
	User savedUser =dao.saveUser(user);
		
	ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(),"user saved successfully" ,savedUser);
	
	ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
	
	return re;
	}


	public ResponseEntity<ResponseStructure<User>> findAll() {
		
		List<User> user = dao.findAll();
		
		
		ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(),"All users successfully" ,user);
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
				
	
		
		return re;
	}


	public ResponseEntity<ResponseStructure<User>> findUserById(int id) {
		
		Optional<User> optional = dao.findUserById(id);
		
		if(optional.isEmpty()) {
			
			//exception
		}
		
		User u = optional.get();
			
		ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(),"user get successfully" ,u);
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		
		return re;
	}


	public ResponseEntity<ResponseStructure<User>> deleteUserById(int id) {
		
		Optional<User> optional = dao.findUserById(id);

		if (optional.isEmpty()) {
			//exception
		} 
		User del = dao.deleteUserById(id);

		ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(), "user found deleted successfully", del);
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re ;
	}


	public ResponseEntity<ResponseStructure<User>> updateUserById(int id, User user) {
		
		Optional<User> optional = dao.findUserById(id);
		if (optional.isEmpty()) {
			
//			throw new InvalidUserIdException("Invalid user id, unable to find");
			throw new RuntimeException("Invalid user id, unable to find");

			
		} 
		
		
		
		
		
		User up = dao.updateUserById(user);
		up.setEmail("abg@gmail.com");

		ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(), "user found deleted successfully", up);
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re ;

			}


	public ResponseEntity<ResponseStructure<User>> findMatch(int id, int top) {
		
		Optional<User> optional = dao.findUserById(id);
		if (optional.isEmpty()) {
			
//			throw new InvalidUserIdException("Invalid user id, unable to find");
			
			throw new RuntimeException("Invalid user id, unable to find");

			
		} 
		
		User user = optional.get();
		
		String gf = null;
		
		if(user.getGender().equalsIgnoreCase("MALE"))
		{
			gf = "FEMALE";
		}
		else
			gf = "MALE";
		
		List<User> users = dao.findByGender(gf);
		
		List<MatchingUser> matchingUsers = new ArrayList<>();
		
		
		for(User u : users) {
			
			MatchingUser mu = new MatchingUser();
			
			mu.setId(u.getId());
			mu.setEmail(u.getEmail());
			mu.setName(u.getName());
			mu.setAge(u.getAge());
			mu.setGender(u.getGender());
			mu.setPassword(u.getPassword());
			mu.setInterests(u.getInterests());
			
			
			int ageDiff = user.getAge() - u.getAge();
			
			// to avod negative values
			
			int absoluteagediff = Math.abs(ageDiff);
			
			
			
			mu.setAgedifference(absoluteagediff);
			
			
			int matchingInterestCount = 0;
			
			List<String> interest1 = user.getInterests();
			List<String> interest2 = u.getInterests();
			
			for (String i : interest1) {
				
				if(interest2.contains(i));
				matchingInterestCount++;
				
			}
			mu.setMatchingIntrestCount(matchingInterestCount);
			
			
			matchingUsers.add(mu);
			
			
		}
		
		
		Collections.sort(matchingUsers, new SortByAgeDiff());
		
		
		List<MatchingUser> result = new ArrayList<>();
		
		for (MatchingUser mu : matchingUsers) {
			
			if(top == 0) {
				break;
			}
			else {
				
				result.add(mu);
				top--;
			}
			
		}
		
		
		
		ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(), "Top users found successfully", result);
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re ;	
		
		
		
	}

}
