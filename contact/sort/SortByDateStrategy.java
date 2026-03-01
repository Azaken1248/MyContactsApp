package com.seveneleven.mycontactapp.contact.sort;

import com.seveneleven.mycontactapp.contact.model.Contact;
import java.util.Comparator;
import java.util.List;

/**
 * Class to sort contacts by date added 
 */
public class SortByDateStrategy implements ContactSortStrategy {
	
	/**
	 * Method to sort contacts by date added
	 * 
	 * @param contacts	List of contacts to be sorted
	 */
    @Override
    public void sort(List<Contact> contacts) {
        contacts.sort(Comparator.comparing(Contact::getTimeStamp).reversed());
    }
}