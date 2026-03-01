package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.model.PhoneNumber;

/**
 * Filter to search based on phone numbera
 */
public class PhoneCriteria implements SearchCriteria{
	private final String targetNumber;
	
	/**
	 * Constructor to create a new phone number filter
	 * 
	 * @param targetNumner	The phone number to be searched
	 */
	public PhoneCriteria(String targetNumber) {
		this.targetNumber = targetNumber.replaceAll("[^0-9+]", "");
	}
	
	/**
	 * Test method to find the phone number in the contact
	 * 
	 * @param contact	The contact against which the filter is used
	 */
	@Override
	public boolean test(Contact contact) {
		for(PhoneNumber phone : contact.getPhoneNumbers()) {
			if(phone.getPhoneNumber().replaceAll("[^0-9+]", "").contains(targetNumber)) {
				return true;
			}
		}
		
		return false;
	}

}
