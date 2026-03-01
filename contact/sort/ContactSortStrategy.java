package com.seveneleven.mycontactapp.contact.sort;

import java.util.List;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Strategy interface for representing sort strategies
 */
public interface ContactSortStrategy {
	/**
	 * Abstract method to sort a list of contacts
	 * 
	 * @param contacts	The list of contacts to be sorted
	 */
	void sort(List<Contact> contacts);
}
