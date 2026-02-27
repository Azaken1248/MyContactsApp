package com.seveneleven.mycontactapp.user.model;

import java.util.ArrayList;
import java.util.List;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * An abstract class that represents a user in the contacts.
 * 
 * Encapsulates the core user data and defines the contact for
 * specific user types.
 *  
 */
public abstract class User {
	
	// Define user attributes;
	private String email;
	private String passwordHash;
	private UserProfile profileInfo;
	private List<Contact> contacts;
	
	/**
	 * Protected constructor to enforce object creation only via the UserBuilder.
	 * 
	 * @param builder the UserBuilder containing the configured user data.
	 */
	protected User(UserBuilder builder) {
		this.email = builder.getEmail();
		this.passwordHash = builder.getPasswordHash();
		this.profileInfo = builder.getProfileInfo();
		this.contacts = new ArrayList<>();
	}
	
	/**
	 * Get the email address of the user.
	 * 
	 * @return The user's email. (String)
	 */
	public String getEmail() { return email; }
	
	/**
	 * Get the password hash of the user's password
	 * 
	 * @return The hash of the user's password (String)
	 */
	public String getPasswordHash() { return passwordHash; }
	
	/**
	 * Get the profile information of the user.
	 * 
	 * @return The information about the user profile (String)
	 */
	public UserProfile getProfileInfo() { return profileInfo; }
	
	/**
	 * Get the contact list of the user.
	 * 
	 * @return The contact about the user profile (List\<Contact\>)
	 */
	public List<Contact> getContacts() { return contacts; }
	
	/**
	 * Method to update/reset the password of the user
	 * 
	 * @param newPasswordHash	Hash of the new password
	 */
	public void setPasswordHash(String newPasswordHash) {
		if(newPasswordHash == null || newPasswordHash.trim().isEmpty()) {
			throw new IllegalArgumentException("Invalid password hash.");
		}
		
		this.passwordHash = newPasswordHash;
	}
	
	/**
	 * Abstract method to get the tier/type of user
	 * 
	 * @return The type of account the user has which can be free or premium (String)
	 */
	public abstract String getAccountTier();
}
