package com.sdi1500103sdi1500109.Network;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sdi1500103sdi1500109.Network.Relationship;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
public interface RelationshipRepository extends CrudRepository<Relationship,Integer>{
	@Query("select rel.userTwoEmail from Relationship rel where (rel.userOneEmail = ?1 and rel.status = 'Friends')")
	public ArrayList<String> findFriends1(String email);
	@Query("select rel.userOneEmail from Relationship rel where (rel.userTwoEmail = ?1 and rel.status = 'Friends')")
	public ArrayList<String> findFriends2(String email);
	@Query("select rel from Relationship rel where((rel.userOneEmail = ?1 or rel.userTwoEmail = ?1) and rel.status = 'Pending')")
	public ArrayList<Relationship> findAllPending(String email);
	@Query("select rel from Relationship rel where((rel.userOneEmail = ?1 or rel.userTwoEmail = ?1) and rel.actionUserEmail != ?1 and rel.status = 'Pending')")
	public ArrayList<Relationship> findUsersPendingFRequests(String email);
	public Relationship findByUserOneEmailAndUserTwoEmail(String email1, String email2);
	public Optional<Relationship> findById(Integer id);
	public void deleteById(Integer id);
	
	


}
