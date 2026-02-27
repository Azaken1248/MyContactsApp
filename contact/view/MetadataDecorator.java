package com.seveneleven.mycontactapp.contact.view;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Decorator that displays the metadata of the contact with formatting
 */
public class MetadataDecorator extends ContactViewDecorator{
	
	/**
	 * Pass the view and contact to parent constructor
	 * 
	 * @param view
	 * @param contact
	 */
	public MetadataDecorator(ContactView view, Contact contact) {
		super(view, contact);
	}
	
	/**
	 * Format the contact
	 */
	@Override
	public String display() {
		String baseDisplay = view.display();
		String timeAdded = contact.getTimeStamp().toString().substring(0, 16).replace("T", " ");
		
		return String.format(
				"=================================================\n" +
			    "%s\n" +
				"Added: %s\n" +
			    "ID: %s\n" +
			    "=================================================",
			    baseDisplay, timeAdded, contact.getId().toString());
	}
}
