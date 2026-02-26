package com.seveneleven.mycontactapp.auth.strategy;

import java.util.Optional;
import java.util.Map;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * Concrete strategy for handling standar email password authentication 
 */
public class OAuthStrategy implements Authentication{
	
	private final Map<String, User> userDatabase;
	
	/**
	 * Constructs the OAuthStrategy 
	 * 
	 * @param userDatabase	The map where registered users are saved
	 */
	public OAuthStrategy(Map<String, User> userDatabase) {
		this.userDatabase = userDatabase;
	}
	
	/**
	 * A method to athenticate a user against given credentials
	 * @param identifier	The identifier of the user (email)
	 * @param secret	The user's secret (password for basic auth and token for Oauth)
	 * @return The user if succeded or Optional.empty() if failed (Optional\<User\>)
	 */
	@Override
	public Optional<User> authenticate(String email, String token) {
		
		User user = userDatabase.get(email);
		if(user == null) {
			return Optional.empty();
		}
		
		if(isValidOAuthToken(token)) {
			return Optional.of(user); // Login Success
		}
		
		return Optional.empty(); // Login Failed
	}
	
	/**
	 * Dummy helper method to validate the OAuth token
	 * 
	 * @param token	Token given by token provider
	 * @return	If the token is valid or not
	 */
	private boolean isValidOAuthToken(String token) {
		return token != null && token.startsWith("contacts_");
	}

}
