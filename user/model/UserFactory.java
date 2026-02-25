package com.seveneleven.mycontactapp.user.model;

/**
 * A factory class responsible for instantiating the correct user subclass
 */
public class UserFactory {
	
	/**
	 * Creates a specific User instance based on requested type.
	 * 
	 * @param type	The type of the user(free/premium)
	 * @param type The UserBuilder containing data to inject into the User.
	 * @return A concrete object based on the requested user type (FreeUser/PremiumUser)
	 */
	public static User createUser(String type, UserBuilder builder) {
		if("PREMIUM".contentEquals(type)) {
			return new PremiumUser(builder);
		}
		
		return new FreeUser(builder);
	}

}
