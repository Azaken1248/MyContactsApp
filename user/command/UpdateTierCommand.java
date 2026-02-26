package com.seveneleven.mycontactapp.user.command;

import java.util.Map;

import com.seveneleven.mycontactapp.auth.session.SessionManager;
import com.seveneleven.mycontactapp.user.model.PremiumUser;
import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.model.UserBuilder;

/**
 * Creates a command to update the tier of the user
 */
public class UpdateTierCommand implements ProfileCommand {
	private final Map<String, User> userDatabase;
	private final User oldUser;
	private User upgradedUser;
	
	/**
	 * To create a command to update the tier of a user
	 * 
	 * @param userDatabase	The user DB
	 * @param currentUser	The current user
	 */
	public UpdateTierCommand(Map<String, User> userDatabase, User currentUser) {
		this.userDatabase = userDatabase;
		this.oldUser = currentUser;
	}
	
	/**
	 * Executes the tier change
	 */
	@Override
	public void execute() {
		if(oldUser instanceof PremiumUser) {
			System.out.println("You are already a premium user.");
			return;
		}
		
		this.upgradedUser = new UserBuilder().setEmail(oldUser.getEmail())
											 .setPasswordHash(oldUser.getPasswordHash())
											 .setProfileInfo(oldUser.getProfileInfo())
											 .setUserType("PREMIUM")
											 .build();
		
		userDatabase.put(upgradedUser.getEmail(), upgradedUser);
		SessionManager.getInstance().loginUser(upgradedUser);
		
		System.out.println("Upgraded to premium OAuth features now unlocked!!");
	}
	
	/**
	 * Rolls back the tier change
	 */
	@Override
	public void undo() {
		if(upgradedUser != null) {
			userDatabase.put(oldUser.getEmail(), oldUser);
			SessionManager.getInstance().loginUser(oldUser);
			
			System.out.println("Reverted back to FREE tier");
		}
	}
}
