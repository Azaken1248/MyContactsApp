package com.seveneleven.mycontactapp.user.model;

/**
 * A class that represents a free user account.
 */
public class FreeUser extends User {
	
	/**
	 * Constructs a FreeUser using the provided builder
	 * 
	 * @param builder the UserBuilder containing the configured user data.
	 */
	protected FreeUser(UserBuilder builder) {
		super(builder);
	}
	
	
	/**
	 * Implementation of the getAccountTier method from the User 
	 * abstract class.
	 * 
	 * @return The type of account the user has which is free (String)
	 */
	@Override
	public String getAccountTier() {
		return "FREE";
	}
}
