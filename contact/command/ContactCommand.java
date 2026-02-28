package com.seveneleven.mycontactapp.contact.command;

/**
 * Interface to represent abstarct commands
 */
public interface ContactCommand {
	
	/**
	 * An abstract method to facilitate command execution
	 */
	void execute();
	
	/**
	 * An abstract method to facilitate command rollback
	 */
	void undo();
}
