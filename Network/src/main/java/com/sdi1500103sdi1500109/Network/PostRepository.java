package com.sdi1500103sdi1500109.Network;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer>{

	public List<Post> findAllByOrderByIdDesc();
	
	public ArrayList<Post> findAllByEmailOrderByIdDesc(String email);
}