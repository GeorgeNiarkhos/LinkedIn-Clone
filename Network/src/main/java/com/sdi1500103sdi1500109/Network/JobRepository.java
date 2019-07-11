package com.sdi1500103sdi1500109.Network;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
@Repository
public interface JobRepository extends CrudRepository<Job,Integer>{

	
	public ArrayList<Job> findAllByEmail(String email);
	
	public ArrayList<Job> findAllByOrderByScoreDesc();
    
    
}
