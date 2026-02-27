package com.seveneleven.mycontactapp.contact.model;

/**
 * Class to represent an email address.
 */
public class EmailAddress {
	private final String label;
	private final String email;
	
	/**
	 * Constructor to se the label and email address
	 * 
	 * @param label	The label for the email
	 * @param email	The email address
	 */
	public EmailAddress(String label, String email) {
		this.label = label;
		this.email = email;
	}
	
	/**
	 * Method to get the label of the EmailAddress.
	 * 
	 * @return	The label of the EmailAddress (String)
	 */
	public String getLabel() { return label; }
	
	/**
	 * Method to get the email of the EmailAddress.
	 * 
	 * @return	The email of the emailAddress (String)
	 */
	public String getEmail() { return email; }
	
	/**
	 * Override the toString method to get string representation of the EmailAddress
	 * 
	 * @return the string representation of email address
	 */
	@Override
	public String toString() {
		return String.join(":", label, email);
	}
}
