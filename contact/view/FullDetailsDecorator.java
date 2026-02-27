package com.seveneleven.mycontactapp.contact.view;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.model.EmailAddress;
import com.seveneleven.mycontactapp.contact.model.PhoneNumber;

/**
 * Decorator that displays phone numbers and emails with string formatting
 * 
 */
public class FullDetailsDecorator extends ContactViewDecorator {
	
	/**
	 * Pass the view and contact to parent constructor
	 * 
	 * @param view
	 * @param contact
	 */
	public FullDetailsDecorator(ContactView view, Contact contact) {
		super(view, contact);
	}
	
	/**
	 * Format the contact
	 */
	@Override
	public String display() {
		StringBuilder stringBuilder = new StringBuilder(view.display()) ;
		
		// Format phone numbers
		stringBuilder.append("\nPhones: ");
		if(contact.getPhoneNumbers().isEmpty()) {
			stringBuilder.append("None");
		}else {
			for(PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
				stringBuilder.append(String.format("[%s] %s | ",phoneNumber.getLabel(),phoneNumber.getPhoneNumber()));
			}
		}
		
		// Format phone numbers
		stringBuilder.append("\nEmails: ");
		if(contact.getEmailAddresses().isEmpty()) {
			stringBuilder.append("None");
		}else {
			for(EmailAddress emailAddress : contact.getEmailAddresses()) {
				stringBuilder.append(String.format("[%s] %s | ",emailAddress.getLabel(),emailAddress.getEmail()));
			}
		}
		
		return stringBuilder.toString();
	}
}
