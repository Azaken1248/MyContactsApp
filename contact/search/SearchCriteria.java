package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.Contact;

import java.util.function.Predicate;

/**
 * Interface to inherit the test method from the Predicate class
 * Allows to define a filter to be tested against contact
 */
public interface SearchCriteria extends Predicate<Contact>{
	// inherited boolean test(Contact t) method from predicate
}
