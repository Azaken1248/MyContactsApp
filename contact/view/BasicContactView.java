package com.seveneleven.mycontactapp.contact.view;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * class to display the basic view of a contact
 * 
 */
public class BasicContactView implements ContactView{
	private final Contact contact; // immutatble contact
	
	/**
	 * Constructor to se the contact;
	 * @param contact	The contact to be set
	 */
	public BasicContactView(Contact contact) {
		this.contact = contact;
	}
	
	/**
	 * get the basic contact summary
	 * 
	 * @return basic contact summary (String)
	 */
	@Override
	public String display() {
		return contact.getContactSummary();
	}
	
	/**
	 * Override toString as per industry standard
	 * 
	 * @return the basic contact summary (String)
	 */
	@Override
	public String toString() {
		return display();
	}
	
	
}
