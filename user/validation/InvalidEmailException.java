package com.seveneleven.mycontactapp.user.validation;
/**
 * Custom exception that gets thrown when a user enters an invalid email
 */

@SuppressWarnings("serial")
public class InvalidEmailException extends Exception{
	
	/**
	 * Constructs a new InvalidEmailException
	 * 
	 * @param message	a message suggesting that the email is invalid
	 */
	public InvalidEmailException(String message) {
		super(message);
	}
}
