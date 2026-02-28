package com.seveneleven.mycontactapp.contact.command;

import java.util.List;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Command to facilitae the editing of contacts
 */
public class EditContactCommand implements ContactCommand {
	private final List<Contact> addressBook;
	private final int indexToEdit;
	private final Contact editedContact;
	private ContactMemento memento;
	
	/**
	 * Contructor to create an edit command object
	 * 
	 * @param addressBook	The contact list of the user
	 * @param indexToEdit	The position of the contact the user wants to edit
	 * @param editedContact	The new contact data
	 */
	public EditContactCommand(List<Contact> addressBook, int indexToEdit, Contact editedContact) {
		this.addressBook = addressBook;
		this.indexToEdit = indexToEdit;
		this.editedContact = editedContact;
	}
	
	/**
	 * method to execute the command
	 */
	@Override
	public void execute() {
		this.memento = new ContactMemento(addressBook.get(indexToEdit));
		
		addressBook.set(indexToEdit, editedContact);
		System.out.println("Contact Edited Successfully");
	}
	/**
	 * Method to save the command
	 */
	@Override
	public void undo() {
		if(memento != null) {
			addressBook.set(indexToEdit, memento.getSavedState());
			System.out.println("Rolled back contact Edit");
		}else {
			System.out.println("Nothing to undo!!");
		}
	}
}
