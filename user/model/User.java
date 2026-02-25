package com.seveneleven.mycontactapp.user.model;

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
	
	/**
	 * Protected constructor to enforce object creation only via the UserBuilder.
	 * 
	 * @param builder the UserBuilder containing the configured user data.
	 */
	protected User(UserBuilder builder) {
		this.email = builder.getEmail();
		this.passwordHash = builder.getPasswordHash();
		this.profileInfo = builder.getProfileInfo();
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
	 * Abstract method to get the tier/type of user
	 * 
	 * @return The type of account the user has which can be free or premium (String)
	 */
	public abstract String getAccountTier();
}
