package com.seveneleven.mycontactapp.contact.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Class to log contact deletions.
 */
public class DeletionLogger implements ContactObserver{
	
	/**
	 * A method to log contact deletes
	 */
	@Override
	public void onContactDeleted(Contact contact, boolean isHardDelete) {
		String type = isHardDelete ? "HARD DELETED (Permanent)" : "SOFT DELETED (Move to trash)";
		System.out.println("\n[SYSTEM LOG] Lifecycle Event: Contact '" + contact.getName() + "' was " + type + ".");
	}
}
