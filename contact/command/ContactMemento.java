package com.seveneleven.mycontactapp.contact.command;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Memento pattern to save the state of a contact safely
 */
public class ContactMemento {
	private final Contact savedState;
	
	/**
	 * Copy the sate
	 * 
	 * @param contactToSave
	 */
	public ContactMemento(Contact contactToSave) {
		this.savedState = contactToSave.copy();
	}
	
	/**
	 * Get the savedState
	 * 
	 * @return the saved state of the exam
	 */
	public Contact getSavedState() {
		return savedState;
	}
}
