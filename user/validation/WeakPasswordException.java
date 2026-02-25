package com.seveneleven.mycontactapp.user.validation;
/**
 * Custom exception that gets thrown when a user enters a weak password
 */

@SuppressWarnings("serial")
public class WeakPasswordException extends Exception{
	
	/**
	 * Constructs a new WeakPasswordException
	 * 
	 * @param message	a message suggesting that the password is weak
	 */
	public WeakPasswordException(String message) {
		super(message);
	}
}
