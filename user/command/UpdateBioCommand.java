package com.seveneleven.mycontactapp.user.command;

import com.seveneleven.mycontactapp.user.model.UserProfile;

/**
 * Creates a command to update the bio of a user
 */
public class UpdateBioCommand implements ProfileCommand{
	private final UserProfile profile;
	private final String newBio;
	private String oldBio;
	
	/**
	 * To create a command to update the bio of a user
	 * 
	 * @param profile	The user profile object
	 * @param newBio	The new bio the user wants to use
	 */
	public UpdateBioCommand(UserProfile profile, String newBio) {
		this.profile = profile;
		this.newBio = newBio;
	}
	
	/**
	 * Executes the bio change
	 */
	@Override
	public void execute() {
		this.oldBio = profile.getBio();
		profile.setBio(newBio);
		System.out.println("Bio Updated...");
	}
	
	/**
	 * Rolls back the name change
	 */
	@Override
	public void undo() {
		if(oldBio != null) {
			profile.setBio(oldBio);
			System.out.println("Reverted to old Bio...");
		}
	}
}
