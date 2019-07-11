package com.sdi1500103sdi1500109.Network;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sdi1500103sdi1500109.Network.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends CrudRepository<User,Integer>{

	
	public User findByEmail(String email);

	public ArrayList<User> findByEmailLike(String email);
	
	public ArrayList<User> findByNameLike(String name);
	
	public ArrayList<User> findByNameContainingOrEmailContaining(String name, String email);
	
	public ArrayList<User> findAllByOrderByScoreDesc();
    
}
