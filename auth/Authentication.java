package com.seveneleven.mycontactapp.auth;

import java.util.Optional;

import com.seveneleven.mycontactapp.user.model.User;

/**
 * Strategy interface containing the abstract method 
 * for various authentication strategies(basicAuth and OAuth).
 */
public interface Authentication {
	/**
	 * A method to athenticate a user against given credentials
	 * @param identifier	The identifier of the user (email)
	 * @param secret	The user's secret (password for basic auth and token for Oauth)
	 * @return The user if succeded or Optional.empty() if failed (Optional\<User\>)
	 */
	public Optional<User> authenticate(String identifier, String secret);
}
