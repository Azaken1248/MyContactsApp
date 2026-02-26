package com.seveneleven.mycontactapp.auth.strategy;

import java.util.Map;
import java.util.Optional;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.utilities.PasswordHasher;

/**
 * Concrete strategy for handling standar email password authentication 
 */
public class BasicAuthStrategy implements Authentication{
	
	private final Map<String, User> userDatabase;
	private final PasswordHasher hasher;
	
	/**
	 * Constructs the BasicAuthStrategy 
	 * 
	 * @param userDatabase	The map where registered users are saved
	 * @param hasher		The hasher class that calculates hashes
	 */
	public BasicAuthStrategy(Map<String, User> userDatabase, PasswordHasher hasher) {
		this.userDatabase = userDatabase;
		this.hasher = hasher;
	}
	
	/**
	 * A method to athenticate a user against given credentials
	 * @param identifier	The identifier of the user (email)
	 * @param secret	The user's secret (password for basic auth and token for Oauth)
	 * @return The user if succeded or Optional.empty() if failed (Optional\<User\>)
	 */
	@Override
	public Optional<User> authenticate(String email, String rawPassword){
		
		User user = userDatabase.get(email);
		
		if(user == null) {
			return Optional.empty();
		}
		
		String hashed = hasher.hash(rawPassword);
		if(hashed.equals(user.getPasswordHash())) {
			return Optional.of(user); // Login success
		}
		
		return Optional.empty(); // Login fail
	}
}
