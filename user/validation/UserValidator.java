package com.seveneleven.mycontactapp.user.validation;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Utility class responsible for validating email and password using
 * regular expressions
 */
public class UserValidator {
	
	/**
	 * Regex pattern for standard email address
	 */
	public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	/**
	 * Regex pattern for a password with at least 8 
	 * characters, a capital letter, a small letter
	 * and a special character
	 */
	public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)\\S{8,}$";
	
	/**
	 * Regex pattern for a phone number which can have
	 * a optional + at the start and can be up to 15 digits
	 * in length
	 * 
	 */
	public static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$";
	
	/**
	 * Validates the user's email address against the regex pattern
	 * throws InvalidEmailException if the email is null or empty or invalid
	 * 
	 * @param email The email address to validate
	 * @throws InvalidEmailException if the email is null, empty, or fails regex check
	 */
	public static void validateEmail(String email) throws InvalidEmailException{
		//[Debug] System.out.println("[Debug] Email = " + email);
		if(email == null || email.isEmpty()) {
			throw new InvalidEmailException("Email cannot be empty!!");
		}
		
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches()) {
			throw new InvalidEmailException("Email address format invalid please use valid format: user@example.com");
		}
	}
	
	/**
	 * Validates the user's password against the regex pattern
	 * throws WeakPasswordException if the password doesn't have at least
	 * 8 characters, a small letter, a capital letter and a special character
	 * 
	 * @param password	The password entered by the user
	 * @throws WeakPasswordException if the password is null, empty or weak
	 */
	public static void validatePassword(String password) throws WeakPasswordException{
		if(password == null || password.isEmpty()) {
			throw new WeakPasswordException("Password cannot be empty or null");
		}
		
		Pattern pattern = Pattern.compile(PASSWORD_REGEX);
		Matcher matcher= pattern.matcher(password);
		
		if(!matcher.matches()) {
			throw new WeakPasswordException("The password is too weak please make sure the password has atleast one uppercase character, one lowercase character, one special character and is atleast 8 digits.");
		}
	}
	
	/**
	 * Validates the user phone number which 
	 * can have a optional + at the start and must be 
	 * up to 15 digits in length and the country code
	 * cannot be zero
	 * 
	 * @param phoneNumber	The phone number of the user
	 * @throws InvalidPhoneNumberException	If the phone number is null, empty or invalid
	 */
	public static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{
		if(phoneNumber == null || phoneNumber.isEmpty()) {
			throw new InvalidPhoneNumberException("Phone number cannot be empty or null");
		}
		
		Pattern pattern = Pattern.compile(PHONE_REGEX);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		if(!matcher.matches()) {
			throw new InvalidPhoneNumberException("The phone number is of invalid format please enter a valid phone number.");
		}
		
	}
}
