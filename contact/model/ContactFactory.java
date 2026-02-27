package com.seveneleven.mycontactapp.contact.model;

import com.seveneleven.mycontactapp.contact.model.Organization.OrganizationBuilder;
import com.seveneleven.mycontactapp.contact.model.Person.PersonBuilder;

/**
 * Factory class to create users based on their type
 */
public class ContactFactory {
	
	public static Person createPersonContact(PersonBuilder builder) {
		return new Person(builder);
	}
	
	public static Organization createOrganizationContact(OrganizationBuilder builder) {
		return new Organization(builder);
	}
}
