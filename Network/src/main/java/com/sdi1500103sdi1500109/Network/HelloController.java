/**
 * 
 */
package com.sdi1500103sdi1500109.Network;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


import com.sdi1500103sdi1500109.Network.User;
import com.sdi1500103sdi1500109.Network.UserRepository;
//import com.sdi1500103sdi1500109.Network.Relationship;
import com.sdi1500103sdi1500109.Network.RelationshipRepository;

@CrossOrigin(origins = "http://localhost:4200")
@Controller    // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class HelloController {
	
	
	// REPOSITORIES 
	@Autowired private UserRepository userRepository;
	@Autowired private ChatRepository chatRepository;
	@Autowired private MessageRepository messageRepository;
	@Autowired private JobRepository jobRepository;
	@Autowired private ProposalRepository proposalRepository;
	@Autowired private RelationshipRepository relationshipRepository;
	@Autowired private PostRepository postRepository;
	@Autowired private CommentRepository commentRepository;
	@Autowired private PostLikeRepository postLikeRepository;
	@Autowired private NotificationRepository notificationRepository;

	private JAXBContext contains;
	// REGISTER MAPPING
	
//	Registers user to the database, if user already
//	exists, return null
	@PostMapping(path="/registerUser")
	public @ResponseBody User registerUser ( @RequestBody User msg ) {
		
		User user = new User();
		user.setName( msg.getName() );
		user.setEmail( msg.getEmail() );
		user.setPass( msg.getPass() );
		user.setEduExperience("");
		user.setFilePath("");
		user.setPhone("");
		user.setSkills("");
		user.setPermissions("00000");
		user.setWorkExperience("");
		if ( this.userRepository.findByEmail(user.getEmail()) == null ) {
			this.userRepository.save(user);
			return user;
		}
		return null;
	}
	
	
	// LOGIN MAPPING
	
//	Get user, check for valid credentials and return response
	@PutMapping(path="/loginUser")
	public @ResponseBody User loginUser ( @RequestBody User msg ) {
		
		User user1 = new User();
		user1.setEmail( msg.getEmail() );
		user1.setPass( msg.getPass() );
		
		User user2 = this.userRepository.findByEmail( user1.getEmail() );
		if ( user2 == null ) {
			// user doesn't exist
			return null;
		}
		if ( !user1.getPass().equals(user2.getPass() ) ) {
			// wrong password
			return null;	
		}
		return user2;
		
	}
	
	
	// SETTING MAPPING
	
	// Update users settings, make sure that the new email doesn't already exists in the
	// database	
	@PutMapping(path="/submitSettings")
	public @ResponseBody User changeSettings( @RequestBody User[] msg , @RequestHeader("Auth") String auth) {
		
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		User oldUser = msg[0];
		User intUser = msg[1];
		
		// If user wants to update email
		if ( ! oldUser.getEmail().equals(intUser.getEmail())  ) { 
			User valUser = userRepository.findByEmail(intUser.getEmail());
			// if there is an email update also check if email already exists
			if ( valUser != null ) {
				return null;
			}
		}
		
		User newUser = userRepository.findByEmail(oldUser.getEmail());
		newUser.setEmail(intUser.getEmail());
		newUser.setPass(intUser.getPass());
		
		userRepository.save(newUser);		
		return newUser;
	}
	
	
	// PROFILE MAPPING
	
	// Returns the user info in order to build a specific profile
	@PutMapping(path="/profile")
	public @ResponseBody User getProfile( @RequestBody User msg , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
			
		// Pull user data from database, search by email
		User user = userRepository.findByEmail( msg.getEmail() );
		return user;
	}
	
	// Save users settings, generated from the profile page
	@PutMapping(path="/saveUser")
	public @ResponseBody User saveUser( @RequestBody User msg , @RequestHeader("Auth") String auth) {
		// Pull user data from database, search by email
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		User idUser = userRepository.findByEmail(msg.getEmail());
		msg.setId(idUser.getId());
		userRepository.save(msg);
		return msg;
	}
	
//	@PutMapping(path="/saveImg")
//	public @ResponseBody Integer saveFile(@RequestBody MultipartFile file, @RequestHeader("Auth") String auth) throws IOException {
//		StringTokenizer st = new StringTokenizer(auth, ":");
//        String useremail = st.nextToken();
//        String password = st.nextToken();
//        User authUser = this.userRepository.findByEmail(useremail);
//        if (authUser == null) {
//        	return null;
//        }
//        else {
//        	if (!( authUser.getPass().equals(password))) {
//        		return null;
//        	}
//        }
//        String name;
//        User user = this.userRepository.findByEmail(useremail);
//        final Path imgsLocation = Paths.get("C:/files");
//        if(!Files.exists(imgsLocation)) {
//            new File("C:/files").mkdir();
//        }
//        
//        if(file.getContentType().contains("image")) {
//        	if(user.getFilePath() != null) {
//        		name = user.getFilePath();
//        	}
//        	else {
//	        	try (Stream<Path> files = Files.list(Paths.get("C:/files"))) {
//	                long id = files.count();
//	                name = Long.toString(id);
//	            }
//        	}
//        	Files.copy(file.getInputStream(), imgsLocation.resolve(name),StandardCopyOption.REPLACE_EXISTING);
//        	
//        	user.setFilePath(name);
//        	this.userRepository.save(user);
//        	
//        	return 0;
//        }
//        
//        
//        return 1;
//        
//	}
//	
//	
//	@GetMapping(path="/getFile/{fileName}")
//    public @ResponseBody byte[] getFile (@PathVariable String fileName){
//        final Path fileLocation = Paths.get("C:/files");
//        String filepath = fileLocation.toAbsolutePath().toString()+"/"+fileName;
//        Path path = Paths.get(filepath);
//        byte[] data = null;
//        try {
//            data = Files.readAllBytes(path);
//        } catch (IOException error) {
//            error.printStackTrace();
//        } 
//        return data;
//    }
	
	
	// MESSAGES MAPPING
	
	// Returns all the user conversations without the conversation content
	@PutMapping(path="/getChats")
	public @ResponseBody List<Chat> getChats( @RequestBody String body , @RequestHeader("Auth") String auth) {
//	    Pull user data from database, search by email
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		User user = userRepository.findByEmail(body);
	
		List<Chat> chats = this.chatRepository.findByUser1OrUser2OrderByIdDesc(user.getEmail(),user.getEmail());
		return chats;	
	}
	
	// Returns the content of a given conversation
	@PutMapping(path="/getMessages")
	public @ResponseBody List<Message> getMessages( @RequestBody Integer body , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		List<Message> messages = messageRepository.findAllByChatId(body);
		return messages;
	}
	
	// Create a new user to user empty conversation
	@PostMapping(path="/createChat")
	public @ResponseBody Integer createChat( @RequestBody Chat chat , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		// Make sure that the chat doesn't exist
		List<Chat> list1 = this.chatRepository.findAllByUser1AndUser2OrderByIdDesc(chat.getUser1(), chat.getUser2());
		List<Chat> list2 = this.chatRepository.findAllByUser1AndUser2OrderByIdDesc(chat.getUser2(), chat.getUser1());
		if ( list1.size() > 0 || list2.size() > 0 ) {
			return 1;
		}
		this.chatRepository.save(chat);
		return 0;
		
	}
	
	// Send a message in a given conversation
	@PostMapping(path="/sendMessage")
	public @ResponseBody Message sendMessage( @RequestBody Message message , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		this.messageRepository.save(message);
		return message;
	}
	
	
	// ADMIN MAPPING
	
	// Returns all users
	@GetMapping(path="/getUsers")
	public @ResponseBody Iterable<User> getUsers() {
		return this.userRepository.findAll();
	}
	
	@GetMapping(path="/getName/{emai}")
	public @ResponseBody String getName( @PathVariable String email ) {
		User user =  this.userRepository.findByEmail(email);
		return user.getName();
	}
	
	// Generate an XML file that the user will be able to download
	@GetMapping(path="/getXml")
	public @ResponseBody ResponseEntity<Resource> getXml(@RequestHeader("Auth") String auth) throws JAXBException{
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        if (!( useremail.equals("admin") && password.equals("admin") )) {
        	return null;
        }
		
		File file = new File("file.xml");
        file.delete();

        this.contains = JAXBContext.newInstance(xmlNetwork.class);
        Marshaller marshaller = this.contains.createMarshaller();
        xmlNetwork xml = new xmlNetwork();
        Iterable <User> users = this.userRepository.findAll();
		
        for(User user : users) {
        	xmlUser tempUser = new xmlUser();
        	tempUser.setxmlUser(user);
        	xml.addXmlUser(tempUser);
        	

        	tempUser.setxmlFriends(this.getAllFriends(user.getEmail(), auth));
        	tempUser.setxmlPosts(this.postRepository.findAllByEmailOrderByIdDesc(user.getEmail()));
        	tempUser.setxmlPostLikes(this.postLikeRepository.findAllByEmail(user.getEmail()));
        	tempUser.setxmlJobs(this.getJobs(user.getEmail(), auth));
        	tempUser.setxmlComments(this.commentRepository.findAllByEmail(user.getEmail()));        	
        }     
        
        marshaller.marshal(xml, new File("file.xml"));
        Path xmlFile = Paths.get("file.xml");
	    Resource resource = null;
	    try {
	        resource = new UrlResource(xmlFile.toUri());
	    } catch (MalformedURLException error) {
	        error.printStackTrace();
	    }
	    if (resource.exists() || resource.isReadable()) {
	    	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=\"" + resource.getFilename() + "\"").header(HttpHeaders.CONTENT_TYPE,"application/xml").body(resource);
	    }
		return null;
        
	}
	
	
	// JOBS MAPPING
	
	// Create a new job in the database 
	@PostMapping(path="/newJob")
	public @ResponseBody Job newJob( @RequestBody Job job , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		this.jobRepository.save(job);
		return job;
	}
	
	// Get jobs for a specific user
	@GetMapping(path="/getJobs/{email}")
	public @ResponseBody ArrayList<Job> getJobs( @PathVariable String email , @RequestHeader("Auth") String auth) {		
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		return this.jobRepository.findAllByEmail(email);
	}
	
	@GetMapping(path="/getMarket/{email}")
	public @ResponseBody List<Job> getMarket( @PathVariable String email , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
        User user = this.userRepository.findByEmail(email);
        String skills = user.getSkills();
        Integer count = 0;
        Iterable<Job> jobs = this.jobRepository.findAll();
        	for(Job job : jobs) {
            	job.setScore(0);
            	count = 0;
            	StringTokenizer sk = new StringTokenizer(skills, ", .");
            	while(sk.hasMoreTokens()) {
    		    	String temp = sk.nextToken();
    		    	if(job.getTitle().toLowerCase().contains(temp.toLowerCase()) && temp.length() >= 2) {
    		    		count = count + 5;
    		    	}
    		    	if(job.getDescription().toLowerCase().contains(temp.toLowerCase())  && temp.length() >= 2 ) {
    		    		count = count + 2;
    		    	}  		    	   		    	
    		    }
            	job.setScore(count);
          
            	this.jobRepository.save(job);
            }
        
        return this.jobRepository.findAllByOrderByScoreDesc();
	}
	
	@PostMapping("/sendProposal")
	public @ResponseBody Proposal sendProposal( @RequestBody Proposal proposal , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		this.proposalRepository.save(proposal);
		return proposal;
		
	}
	
	@GetMapping(path="/getProposals/{jobId}")
	public @ResponseBody List<Proposal> getProposals( @PathVariable Integer jobId , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		return this.proposalRepository.findAllByJobId(jobId);
		
		
	}
	
	// NETWORK MAPPING		
	
	@GetMapping(path="/network/{userEmail}")
	public @ResponseBody ArrayList<User> getAllFriends(@PathVariable String userEmail, @RequestHeader("Auth") String auth ) {
		// This returns a JSON or XML with the friends
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		ArrayList<User> friends = new ArrayList<User>();
		ArrayList<String> emails1 = this.relationshipRepository.findFriends1(userEmail);
		ArrayList<String> emails2 = this.relationshipRepository.findFriends2(userEmail);
		
		if (!(emails1.isEmpty())) {
			for(int i=0; i < emails1.size(); i++ ) {
				friends.add(this.userRepository.findByEmail(emails1.get(i)));
			}			
		}
		if (!(emails2.isEmpty())) {
			for(int i=0; i < emails2.size(); i++ ) {
				friends.add(this.userRepository.findByEmail(emails2.get(i)));
			}		
		}
		
		if(friends.isEmpty()) {
			return null;
		}
		else {
			return friends;
		}
		
	}
	
	
	@GetMapping(path="/search/{keyword}")
	public @ResponseBody ArrayList<User> getUsersByKeword( @PathVariable String keyword, @RequestHeader("Auth") String auth) {
		// This returns a JSON or XML with the searched users
		
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		ArrayList<User> searchedUsers = this.userRepository.findByNameContainingOrEmailContaining(keyword, keyword);
		
		if(searchedUsers.isEmpty()) {
			return null;
		}
		else {
			return searchedUsers;
		}
				
	}
	
	@GetMapping(path="/requests/{email}")
	public @ResponseBody ArrayList<Relationship> getUsersFRequests( @PathVariable String email, @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		ArrayList<Relationship> requests = this.relationshipRepository.findUsersPendingFRequests(email);
		if(requests.isEmpty()) {
			return null;
		}
		else {
			return requests;
		}
		
	}
	
	@DeleteMapping(path="/decline/{id}")
	public @ResponseBody Integer declineRequest ( @PathVariable Integer id, @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		Optional<Relationship> rel = this.relationshipRepository.findById(id);
		if (rel.isPresent()) {
			this.relationshipRepository.deleteById(id);
			return 0;
		}
		else {
			return 1;
		}
		
		
	}
	
	@PostMapping(path="/add")
	public @ResponseBody Integer addFriend ( @RequestBody Relationship rel, @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		if(this.relationshipRepository.findByUserOneEmailAndUserTwoEmail(rel.getUserOneEmail(), rel.getActionUserEmail()) == null) {
			if (this.relationshipRepository.findByUserOneEmailAndUserTwoEmail(rel.getActionUserEmail(), rel.getUserOneEmail()) != null) {
				return 1;
			}
		}
		else {
			return 1;
		}
		
		rel.setUserTwoEmail(rel.getActionUserEmail());
		rel.setStatus("Pending");
		this.relationshipRepository.save(rel);
		return 0;
	}
	
	@PutMapping(path="/accept")
	public @ResponseBody Integer acceptRequest ( @RequestBody Integer id, @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		Optional<Relationship> rel = this.relationshipRepository.findById(id);
		if(!(rel.isPresent())) {
			return 2;
		}
		if(!(rel.get().getStatus().equals("Friends"))) {
			rel.get().setStatus("Friends");
			this.relationshipRepository.save(rel.get());
			return 0;
		}
		else {
			return 1;
		}
	}
	
	// POST MAPPING
	
	@PostMapping(path="/newPost")
	public @ResponseBody Post post( @RequestBody Post post , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		this.postRepository.save(post);
		return post;
		
	}
	
	@GetMapping(path="/getPosts/{email}")		
	public @ResponseBody List<Post> getPosts(@PathVariable String email, @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		
		ArrayList<User> users = (ArrayList<User>) this.userRepository.findAll();
		Relationship rel = new Relationship();
		for ( User user : users ) {
			
			user.setScore(0);
			// get bonus based on friendship
			if( (rel =this.relationshipRepository.findByUserOneEmailAndUserTwoEmail(email, user.getEmail() ) ) == null) {
				if ( ( rel = this.relationshipRepository.findByUserOneEmailAndUserTwoEmail(user.getEmail(), email ) ) == null) {
				}
				else {
					if ( rel.getStatus().equals("Friends") ) {
						user.setScore( user.getScore() + 40 );
					} else {
						user.setScore( user.getScore() + 5 );
					}
				}
			}
			else {
				if ( rel.getStatus().equals("Friends") ) {
					user.setScore( user.getScore() + 40 );
				} else {
					user.setScore( user.getScore() + 5 );
				}
			}
			
			// get all posts by current user in vector
			ArrayList<Post> posts = this.postRepository.findAllByEmailOrderByIdDesc(user.getEmail());
			for ( Post post : posts ) {
				// For each post check all comments
				ArrayList<Comment> comments = (ArrayList<Comment>) this.commentRepository.findAllByPostIdOrderByIdDesc(post.getId());
				for ( Comment comment : comments ) {
					if ( comment.getEmail().equals(email) ) {
						user.setScore( user.getScore() + 5 );
					}
				}
				// For each post check all likes
				ArrayList<PostLike> likes = (ArrayList<PostLike>) this.postLikeRepository.findByPostIdOrderByIdDesc(post.getId());
				for ( PostLike like : likes ) {
					if ( like.getEmail().equals(email) ) {
						user.setScore( user.getScore() + 3 );
					}
				}
			}
			
			this.userRepository.save(user);
			
		} // end of user loop
		
		// Create the final posts
		users = this.userRepository.findAllByOrderByScoreDesc();
		ArrayList<Post> finalPosts = new ArrayList<Post>();
		int len = 0;
		if ( users.size() > 10 ) {
			len = users.size() / 2;
		} else if ( users.size() < 5 ) {
			len = users.size();
		} else {
			len = 5;
		}
		for ( int i = 0; i < len; i++ ) {
			ArrayList<Post> posts = this.postRepository.findAllByEmailOrderByIdDesc( users.get(i).getEmail() );
				finalPosts.addAll(posts);
		}
		return finalPosts;
		
	}
	
	
	@PostMapping(path="/comment")
	public @ResponseBody Comment newComment( @RequestBody Comment comment , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		this.commentRepository.save(comment);
		return comment;
	}
	
	@GetMapping(path="/getComments/{postId}")
	public @ResponseBody List<Comment> getComments( @PathVariable Integer postId , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		return (List<Comment>) this.commentRepository.findAllByPostIdOrderByIdDesc(postId);
	}
	
	@GetMapping(path="/getLikes/{postId}")
	public @ResponseBody List<PostLike> getLikes(@PathVariable Integer postId , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		return this.postLikeRepository.findByPostIdOrderByIdDesc(postId);
	}
	
	@PostMapping(path="/like")
	public @ResponseBody PostLike like(@RequestBody PostLike like , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		if ( !this.postLikeRepository.existsByPostIdAndEmail(like.getPostId(), like.getEmail()) ) {
			this.postLikeRepository.save(like);
			return like;
		}
		return null;
	}
	
	@GetMapping(path="/getLikes")
	public @ResponseBody List<PostLike> getLikes( @RequestBody Post post , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		return this.postLikeRepository.findByPostIdOrderByIdDesc( post.getId() );
	}
	
	
	// NOTIFICATION MAPPING
	
	@GetMapping(path="/getNotifications/{email}")
	public @ResponseBody List<Notification> getNotifications( @PathVariable String email , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		return this.notificationRepository.findAllByEmailOrderByIdDesc(email);
	}
	
	// Generate a new notification in the database for a specific user
	@PostMapping(path="/newNotification")
	public @ResponseBody Integer newNotification( @RequestBody Notification notification , @RequestHeader("Auth") String auth) {
		StringTokenizer st = new StringTokenizer(auth, ":");
        String useremail = st.nextToken();
        String password = st.nextToken();
        User authUser = this.userRepository.findByEmail(useremail);
        if (authUser == null) {
        	return null;
        }
        else {
        	if (!( authUser.getPass().equals(password))) {
        		return null;
        	}
        }
		
		this.notificationRepository.save(notification);
		return 0;
	}
	
	
	
}