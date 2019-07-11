package com.sdi1500103sdi1500109.Network;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
public interface ChatRepository extends CrudRepository<Chat,Integer>{
    
	List<Chat> findByUser1OrUser2OrderByIdDesc(String user1, String user2);
    List<Chat> findAllByUser1AndUser2OrderByIdDesc(String user1, String user2);
}