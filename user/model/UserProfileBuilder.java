package com.seveneleven.mycontactapp.user.model;

/**
 * Builder class used to construct UserProfile 
 */
public class UserProfileBuilder {
	private String username;
	private String bio;
	private String phoneNumber;
	
	/**
	 * Method that returns the username of the UserProfileBuilder
	 * 
	 * @return The username from the UserBuilder (String)
	 */
	public String getUsername() { return username; }
	
	/**
	 * Method that returns the bio/status of the UserProfileBuilder
	 * 
	 * @return The bio from the UserBuilder (String)
	 */
	public String getBio() { return bio; }
	
	/**
	 * Method that returns the phone number of the UserProfileBuilder
	 * 
	 * @return The phone number from the UserBuilder (String)
	 */
	public String getPhoneNumber() { return phoneNumber; }
	
	/**
	 * Sets the profile username
	 * 
	 * @param username	The username to be set
	 * @return The current UserProfileBuilder instance (UserProfileBuilder)
	 */
	public UserProfileBuilder setUsername(String username) {
		this.username = username;
		return this;
	}
	
	/**
	 * Sets the profile bio/status
	 * 
	 * @param bio	The bio/status to be set
	 * @return The current UserProfileBuilder instance (UserProfileBuilder)
	 */
	public UserProfileBuilder setBio(String bio) {
		this.bio = bio;
		return this;
	}
	
	/**
	 * Sets the profile phone number
	 * 
	 * @param phoneNumber	The phone number to be set
	 * @return The current UserProfileBuilder instance (UserProfileBuilder)
	 */
	public UserProfileBuilder setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	/**
	 * Creates a fully functional concrete profile object
	 * 
	 * @return A fully functional user profile object (UserProfile)
	 */
	public UserProfile build() {
		return new UserProfile(this);
	}

}
