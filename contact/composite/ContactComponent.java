package com.seveneleven.mycontactapp.contact.composite;

import com.seveneleven.mycontactapp.user.model.User;

/**
 * Interface to define composite object for multiple contacts
 */
public interface ContactComponent {
	/**
	 * Abstract method to add tags to this group of contacts
	 * 
	 * @param tag	The tag to be added
	 */
	void addTag(String tag);
	
	/**
	 * An abstract method to export all contacts in this composition to a CSV
	 * 
	 */
	String exportToCSV();
	
	/**
	 * An abstract method to soft delete all contacts in the composition
	 * 
	 * @param activeUser The current logged in user
	 */
	void performBulkSoftDelete(User activeUser);
}
