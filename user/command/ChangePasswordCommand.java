package com.seveneleven.mycontactapp.user.command;

import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.utilities.PasswordHasher;
import com.seveneleven.mycontactapp.user.validation.UserValidator;

/**
 * Creates a command to change the user password
 */
public class ChangePasswordCommand implements ProfileCommand {
	private final User user;
	private final String newRawPassword;
	private final PasswordHasher hasher;
	private String oldPasswordHash;
	
	/**
	 * To create a command to change the password of a user
	 * 
	 * @param user	The user object
	 * @param newRawPassword	The new password
	 * @param hasher	The password hasher utility
	 */
	public ChangePasswordCommand(User user, String newRawPassword, PasswordHasher hasher) {
		this.user = user;
		this.newRawPassword = newRawPassword;
		this.hasher = hasher;
	}
	
	/**
	 * Executes the password change
	 */
	@Override
	public void execute() {
		try {
			UserValidator.validatePassword(newRawPassword);
			
			this.oldPasswordHash = user.getPasswordHash();
			
			String newHash = hasher.hash(newRawPassword);
			user.setPasswordHash(newHash);
			System.out.println("Password changed successfully!!");
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	/**
	 * Rolls back the password change
	 */
	@Override
	public void undo() {
		if(oldPasswordHash != null) {
			user.setPasswordHash(oldPasswordHash);
			System.out.println("Old password restord");
		}
	}
	
}
