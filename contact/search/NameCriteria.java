package com.seveneleven.mycontactapp.contact.search;

import java.util.regex.Pattern;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Filter to search based on names
 */
public class NameCriteria implements SearchCriteria{
	private final Pattern pattern;
	
	/**
	 * Constructor to create a new name filter
	 * 
	 * @param searchQuery	The name to be searched
	 */
	public NameCriteria(String searchQuery) {
		this.pattern = Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE);
	}
	
	/**
	 * Test method to find the name in the contact
	 * 
	 * @param contact	The contact against which the filter is used
	 */
	@Override
	public boolean test(Contact contact) {
		return pattern.matcher(contact.getName()).find();
	}

}
