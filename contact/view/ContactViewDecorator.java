package com.seveneleven.mycontactapp.contact.view;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Abstract class that acts as the base class for all decorators.
 * 
 */
public class ContactViewDecorator implements ContactView{
	protected final ContactView view;
	protected final Contact contact;
	
	/**
	 * Constructor to create the abstract ContactViewDecorator
	 * 
	 * @param view	An instance of contact view
	 * @param contact	the contact
	 */
	public ContactViewDecorator(ContactView view, Contact contact) {
		this.view = view;
		this.contact = contact;
	}
	
	/**
	 * Display the contact
	 */
	@Override
	public String display() {
		return view.display();
	}
	
	/**
	 * Override toString to display the contact 
	 */
	@Override
	public String toString() {
		return display();
	}
}
