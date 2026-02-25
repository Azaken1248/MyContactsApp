package com.seveneleven.mycontactapp.user.validation;

@SuppressWarnings("serial")
public class InvalidPhoneNumberException extends Exception{
	public InvalidPhoneNumberException(String message) {
		super(message);
	}
}
