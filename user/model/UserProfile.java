package com.seveneleven.mycontactapp.user.model;

/**
 * Represents and displays the personal information of the user
 */
public class UserProfile {
	private String username;
	private String bio;
	private String phoneNumber;
	
	/**
	 * Protected constructor to enforce object creation only via the UserProfileBuilder.
	 * 
	 * @param builder the UserProfileBuilder containing the configured user data.
	 */
	protected UserProfile(UserProfileBuilder builder) {
		this.username = builder.getUsername();
		this.bio = builder.getBio();
		this.phoneNumber = builder.getPhoneNumber();
	}
	
	/**
	 * Get the display name of the user.
	 * 
	 * @return The user's display name. (String)
	 */
	public String getUsername() { return username; }
	
	/**
	 * Get the bio of the user.
	 * 
	 * @return The user's bio. (String)
	 */
	public String getBio() { return bio; }
	
	/**
	 * Get the phone number of the user.
	 * 
	 * @return The user's phone number. (String)
	 */
	public String getPhoneNumber() { return phoneNumber; }
	
	/**
	 * Get the string representation of the user profile.
	 * 
	 * @return The UserProfile object. (String)
	 */
	@Override
	public String toString() {
		return String.join("|", username, bio, phoneNumber);
	}
}
