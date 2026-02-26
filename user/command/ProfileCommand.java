package com.seveneleven.mycontactapp.user.command;

/**
 * Interface to represent abstarct commands
 */
public interface ProfileCommand {
	
	/**
	 * An abstract method to facilitate command execution
	 */
	void execute();
	
	/**
	 * An abstract method to facilitate command rollback
	 */
	void undo();
}
