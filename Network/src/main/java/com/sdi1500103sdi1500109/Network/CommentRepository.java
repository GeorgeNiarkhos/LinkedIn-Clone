package com.sdi1500103sdi1500109.Network;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Integer>{

	public List<Comment> findAllByPostIdOrderByIdDesc(Integer postId);
	
	public ArrayList<Comment> findAllByEmail(String email);
	
}