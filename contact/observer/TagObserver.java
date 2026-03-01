package com.seveneleven.mycontactapp.contact.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.tag.Tag;

/**
 * Interface for notifications on tag actions
 */
public interface TagObserver {
	
	/**
	 * Abstract method to perform actions on state changes
	 * 
	 * @param contact	The contact to which tag has to added
	 * @param Tag	The tag that has to be added
	 */
	void onTagAdded(Contact contact, Tag tag);
	
	/**
	 * Abstract method to perform actions on state changes
	 * 
	 * @param contact	The contact to which tag has to removed
	 * @param Tag	The tag that has to be removed
	 */
	void onTagRemoved(Contact contact, Tag tag);
}
