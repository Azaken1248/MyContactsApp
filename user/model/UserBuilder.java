package com.seveneleven.mycontactapp.user.model;

/**
 * Builder class used to construct User objects 
 */
public class UserBuilder {
	private String email;
	private String passwordHash;
	private UserProfile profileInfo;
	private String userType = "FREE";

	/**
	 * Method that returns the email of the UserBuilder
	 * 
	 * @return The email from the UserBuilder (String)
	 */
	public String getEmail() { return email; }

	/**
	 * Method that returns the hashed password of the UserBuilder
	 * 
	 * @return The hashed password from the UserBuilder (String)
	 */
	public String getPasswordHash() { return passwordHash; }

	/**
	 * Method that returns the profile information of the UserBuilder
	 * 
	 * @return The profile information from the UserBuilder (String)
	 */
	public UserProfile getProfileInfo() { return profileInfo; }


	/**
	 * Sets the user's email
	 * 
	 * @param email	The email address to be set
	 * @return The current UserBuilder instance (UserBuilder)
	 */
	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Sets the user's hashed password
	 * 
	 * @param passwordHash	The hashed password to be set
	 * @return The current UserBuilder instance (UserBuilder)
	 */
	public UserBuilder setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	/**
	 * Sets the user's profile information
	 * 
	 * @param profileInfo	The profile information to be set
	 * @return The current UserBuilder instance (UserBuilder)
	 */
	public UserBuilder setProfileInfo(UserProfile profileInfo) {
		this.profileInfo = profileInfo;
		return this;
	}

	/**
	 * Sets the user's profile type
	 * 
	 * @param type	The type of profile to be created to be set
	 * @return The current UserBuilder instance (UserBuilder)
	 */
	public UserBuilder setUserType(String type) {
		this.userType = type;
		return this;
	}

	/**
	 * Creates a fully functional concrete user object
	 * 
	 * @return A fully functional user object (FreeUser/PremiumUser)
	 */
	public User build() {
		return UserFactory.createUser(this.userType, this);
	}
}
