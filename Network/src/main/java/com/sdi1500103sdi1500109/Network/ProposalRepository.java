package com.sdi1500103sdi1500109.Network;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
@Repository
public interface ProposalRepository extends CrudRepository<Proposal,Integer>{
 
	List<Proposal> findAllByJobId(Integer jobId);
    
}
