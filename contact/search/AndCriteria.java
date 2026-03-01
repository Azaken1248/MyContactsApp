package com.seveneleven.mycontactapp.contact.search;

import java.util.List;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Composition of multiple filters
 * To create a filter pipeline
 */
public class AndCriteria implements SearchCriteria{
	private final List<SearchCriteria> criteriaChain;
	
	/**
	 * Constructor to invoke the chaining pipeline
	 * 
	 * @param criteriaChain	The current criteria chain
	 */
	public AndCriteria(List<SearchCriteria> criteriaChain) {
		this.criteriaChain = criteriaChain;
	}
	
	/**
	 * Test method to test criteria against contact
	 */
	@Override
	public boolean test(Contact contact) {
		for(SearchCriteria criteria : criteriaChain) {
			if(!criteria.test(contact)) {
				return false;
			}
		}
		
		return true;
	}
}
