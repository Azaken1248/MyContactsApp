package com.seveneleven.mycontactapp.user.command;

import com.seveneleven.mycontactapp.user.model.UserProfile;

/**
 * Creates a command to update the phone number of the user
 */
public class UpdatePhoneNumberCommand implements ProfileCommand{
	private final UserProfile profile;
	private final String newPhoneNumber;
	private String oldPhoneNumber;
	
	/**
	 * To create a command to update the phone number of a user
	 * 
	 * @param profile	The user profile object
	 * @param newPhoneNumber	The new phone number the user wants to use
	 */
	public UpdatePhoneNumberCommand(UserProfile profile, String newPhoneNumber) {
		this.profile = profile;
		this.newPhoneNumber = newPhoneNumber;
	}
	
	/**
	 * Executes the phone number change
	 */
	@Override
	public void execute() {
		this.oldPhoneNumber = profile.getPhoneNumber();
		profile.setPhoneNumber(newPhoneNumber);
		System.out.println("Phone number updated...");	
	}
	
	/**
	 * Rolls back the phone number change
	 */
	@Override
	public void undo() {
		if(oldPhoneNumber != null) {
			profile.setPhoneNumber(oldPhoneNumber);
			System.out.println("Reverted to old phone number...");
		}
	}
	
	
}
