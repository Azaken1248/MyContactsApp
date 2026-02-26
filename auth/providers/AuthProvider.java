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
	
	// Acts as the data store for gov servers
	private final static Map<String, ExternalData> providerDatabase = new HashMap<>();
	
	static {
		// Add dummy data
		providerDatabase.put("rohit@gmail.com", new ExternalData("2161-6729-3627", "HDFC-Acct-998877"));
		providerDatabase.put("rahul@gmail.com", new ExternalData("8384-2795-9970", "SBI-Acct-112233"));
	}
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
	
	/**
	 * Acts as a resource endpoint from provider
	 * 
	 * @param token	OAuth token of the user
	 * @return	return the relevant data from the database (ExternalData)
	 */
	public static ExternalData fetchSensitiveData(String token) {
		String email = activeTokens.get(token);
		if(email != null) {
			return providerDatabase.get(email);
		}
		
		return null;
	}
	
	/**
	 * Inner class to emulate secure data transfer from Gov servers to ContactApp
	 */
	public static class ExternalData {
		private final String aadharNumber;
		private final String bankDetails;
		
		/**
		 * Constructor to instantiate the ExternalData
		 * 
		 * @param aadharNumber aadhar number of the user
		 * @param bankDetails	bank details of the user
		 */
		public ExternalData(String aadharNumber, String bankDetails) {
			this.aadharNumber = aadharNumber;
			this.bankDetails = bankDetails;
		}
		
		/**
		 * Get the Aadhar number
		 * 
		 * @return	aadharNumber (String)
		 */
		public String getAadharNumber() { return aadharNumber; }
		
		/**
		 * Get the bank details
		 * 
		 * @return bankDetails (String)
		 */
		public String getBankDetails() { return bankDetails; }
		
	}
}
