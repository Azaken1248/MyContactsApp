package com.seveneleven.mycontactapp.contact.command;


import java.util.Stack;

/**
 * The controller that manages execution, rollback and command history.
 */
public class ContactCommandController {
	private final static Stack<ContactCommand> commandHistory = new Stack<>();
	
	/**
	 * method to execute the command and store into history
	 * 
	 * @param createCommand	The create command object
	 */
	public static void executeCommand(ContactCommand contactCommand) {
		contactCommand.execute();
		commandHistory.push(contactCommand);
	}
	
	/**
	 * Method to undo the previous command
	 */
	public static void undoCommand() {
		if(commandHistory.isEmpty()) {
			System.out.print("Nothing to undo");
			return;
		}
		commandHistory.pop().undo();
	}
}
