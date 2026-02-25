package com.seveneleven.mycontactapp.user.model;

/**
 * A class that represents a premium user account.
 */
public class PremiumUser extends User {
	
	/**
	 * Constructs a PremiumUser using the provided builder
	 * 
	 * @param builder the UserBuilder containing the configured user data.
	 */
	protected PremiumUser(UserBuilder builder) {
		super(builder);
	}
	
	
	/**
	 * Implementation of the getAccountTier method from the User 
	 * abstract class.
	 * 
	 * @return The type of account the user has which is premium (String)
	 */
	@Override
	public String getAccountTier() {
		return "PREMIUM";
	}
}

