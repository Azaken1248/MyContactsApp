package com.seveneleven.mycontactapp.auth.providers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A class to demonstrate the generation and validations of Auth Tokens
 */
public class AuthProvider {
	// Stores all active tokens
	private final static Map<String, String> activeTokens = new HashMap<>();
	
	/**
	 * A function to generate OAuth Tokens
	 * 
	 * @param email	The email of the user
	 * @return	the generated token (String)
	 */
	public static String generateToken(String email) {
		String token = "contacts_" + UUID.randomUUID().toString();
		
		activeTokens.put(token, email);
		
		return token;
	}
	
	/**
	 * A method to validate the OAuth token of the user
	 * 
	 * @param token	The OAuth token of the user
	 * @param email	The email of the user
	 * @return	True if the token is valid else false
	 */
	public static boolean isValidToken(String token, String email) {
		String registeredEmail = activeTokens.get(token);
		
		return registeredEmail != null && registeredEmail.equals(email);
	}
}
