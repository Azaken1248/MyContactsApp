package com.seveneleven.mycontactapp.user.command;

import com.seveneleven.mycontactapp.user.model.UserProfile;

/**
 * Command to update the username of the user
 */
public class UpdateUserNameCommand implements ProfileCommand{
	private final UserProfile profile;
	private final String newName;
	private String oldName;
	
	/**
	 * To create a command to update the username of a user
	 * 
	 * @param profile	The user profile object
	 * @param newName	The new name the user wants to use
	 */
	public UpdateUserNameCommand(UserProfile profile, String newName) {
		this.profile = profile;
		this.newName = newName;
	}
	
	/**
	 * Executes the name change
	 */
	@Override
	public void execute() {
		this.oldName = profile.getUsername();
		profile.setUsername(newName);
		System.out.println("Username Updated...");
	}
	
	/**
	 * Rolls back the name change
	 */
	@Override
	public void undo() {
		if(oldName != null) {
			profile.setUsername(oldName);
			System.out.println("Reverted to old username...");
		}
	}
}
