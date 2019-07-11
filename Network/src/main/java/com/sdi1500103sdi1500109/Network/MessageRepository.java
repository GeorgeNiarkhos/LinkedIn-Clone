package com.sdi1500103sdi1500109.Network;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
public interface MessageRepository extends CrudRepository<Message,Integer>{

	public List<Message> findAllByChatId( Integer ChatId );
        
}