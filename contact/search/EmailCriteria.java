package com.seveneleven.mycontactapp.contact.search;

import java.util.regex.Pattern;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.model.EmailAddress;

/**
 * Filter to search based on emails
 */
public class EmailCriteria implements SearchCriteria{
	private final Pattern pattern;
	
	/**
	 * Constructor to create a new Email filter
	 * 
	 * @param searchQuery	The Email to be searched
	 */
	public EmailCriteria(String searchQuery) {
		this.pattern = Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE);
	}
	
	/**
	 * Test method to find the Email in the contact
	 * 
	 * @param contact	The contact against which the filter is used
	 */
	@Override
	public boolean test(Contact contact) {
		for(EmailAddress email : contact.getEmailAddresses()) {
			if(pattern.matcher(email.getEmail()).find()) {
				return true;
			}
		}
		
		return false;
	}

}
