package com.sdi1500103sdi1500109.Network;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLike,Integer> {
	
	List<PostLike> findByPostIdOrderByIdDesc( Integer postId );
	boolean existsByPostIdAndEmail( Integer postId, String email );
	
	public ArrayList<PostLike> findAllByEmail(String email);
}
