package com.seveneleven.mycontactapp.user.command;

import java.util.Stack;

/**
 * The controller that manages execution, rollback and command history.
 */
public class ProfileUpdateController {
	private final static Stack<ProfileCommand> commandHistory = new Stack<>();
	
	/**
	 * method to execute the command and store into history
	 * 
	 * @param profileCommand
	 */
	public static void executeCommand(ProfileCommand profileCommand) {
		try {
			profileCommand.execute();
			commandHistory.push(profileCommand);
			
		}catch(IllegalArgumentException e) {
			System.out.println("Validation error: " + e.getMessage());
		}catch(Exception e) {
			System.out.println("Unexpexted error: " + e.getMessage());
		}
	}
	
	public static void undoCommand() {
		if(commandHistory.isEmpty()) {
			System.out.print("Nothing to undo");
			return;
		}
		
		ProfileCommand previousCommand= commandHistory.pop();
		previousCommand.undo();
	}
}
