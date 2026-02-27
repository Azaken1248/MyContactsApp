package com.seveneleven.mycontactapp.contact.model;

/**
 * Class to represent an phone number.
 */
public class PhoneNumber {
	private final String label;
	private final String phoneNumber;
	
	/**
	 * Constructor to se the label and email address
	 * 
	 * @param label	The label for the email
	 * @param email	The email address
	 */
	public PhoneNumber(String label, String phoneNumber) {
		this.label = label;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Method to get the label of the PhoneNumber.
	 * 
	 * @return	The label of the PhoneNumber (String)
	 */
	public String getLabel() { return label; }
	
	/**
	 * Method to get the phone number of the PhoneNumber.
	 * 
	 * @return	The phone number of the PhoneNumber (String)
	 */
	public String getPhoneNumber() { return phoneNumber; }
	
	/**
	 * Override the toString method to get string representation of the PhoneNumber
	 * 
	 * @return the string representation of phone number
	 */
	@Override
	public String toString() {
		return String.join(":", label, phoneNumber);
	}
}

