package com.seveneleven.mycontactapp.contact.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.tag.Tag;

/**
 * Class to log tag operations
 */
public class TagUpdateLogger implements TagObserver{
	
	/**
	 * Method to perform actions on state changes
	 * 
	 * @param contact	The contact to which tag has to added
	 * @param Tag	The tag that has to be added
	 */
	@Override
	public void onTagAdded(Contact contact, Tag tag) {
		System.out.println("\n[SYSTEM LOG] Tag '" + tag.getName() + "' sucessfully linked to  '" + contact.getName() + "'");
	}
	
	/**
	 * Method to perform actions on state changes
	 * 
	 * @param contact	The contact to which tag has to removed
	 * @param Tag	The tag that has to be removed
	 */
	@Override
	public void onTagRemoved(Contact contact, Tag tag) {
		System.out.println("\n[SYSTEM LOG] Tag '" + tag.getName() + "' sucessfully unlinked from  '" + contact.getName() + "'");
	}
}
