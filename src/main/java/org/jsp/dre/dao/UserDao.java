package org.jsp.dre.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.dre.entity.User;
import org.jsp.dre.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository repo;

	public User saveUser(User user) {
		
		
		
		
		return repo.save(user);
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public Optional<User> findUserById(int id) {
		return repo.findById(id);
	}

	public User deleteUserById(int id) {

		repo.deleteById(id);
		return null;
		
		
	}

	public User updateUserById(User user) {
		return repo.save(user);
	}

	
	public List<User> findByGender(String gf) {
		
		return repo.findByGender(gf);
	}

	
	
	
	
	
	
}
