package com.sdi1500103sdi1500109.Network;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification,Integer>{

	public List<Notification> findAllByEmailOrderByIdDesc(String email);
	
}