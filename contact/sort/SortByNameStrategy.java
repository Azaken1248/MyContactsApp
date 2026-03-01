package com.seveneleven.mycontactapp.contact.sort;

import com.seveneleven.mycontactapp.contact.model.Contact;
import java.util.Comparator;
import java.util.List;

/**
 * Class to sort the contact list by name
 */
public class SortByNameStrategy implements ContactSortStrategy {
	
	/**
	 * Method to sort a contact list name
	 * 
	 * @param contacts	The list of contacts to be sorted
	 */
    @Override
    public void sort(List<Contact> contacts) {
        contacts.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
    }
}