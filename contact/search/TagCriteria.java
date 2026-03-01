package com.seveneleven.mycontactapp.contact.search;


import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Filter to search based on tags
 */
public class TagCriteria implements SearchCriteria {
    private final String targetTag;
    
    /**
	 * Constructor to create a new tag filter
	 * 
	 * @param targetTag	The tag to be searched
	 */
    public TagCriteria(String targetTag) {
        this.targetTag = targetTag.toLowerCase();
    }
    
    /**
	 * Test method to find the tag in the contact
	 * 
	 * @param contact	The contact against which the filter is used
	 */
    @Override
    public boolean test(Contact contact) {
        return contact.getTags().stream()
                .anyMatch(tag -> tag.toLowerCase().equals(targetTag));
    }
}