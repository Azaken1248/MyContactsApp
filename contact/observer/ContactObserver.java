package com.seveneleven.mycontactapp.contact.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Interface for the notifications on actions
 */
public interface ContactObserver {
	
	/**
	 * Abstract method to perform actions on state changes
	 * 
	 * @param contact	The contact to be deleted
	 * @param isHardDelete	Check for hard delete
	 */
	void onContactDeleted(Contact contact, boolean isHardDelete);
}
