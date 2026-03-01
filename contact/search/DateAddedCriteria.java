package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.Contact;
import java.time.LocalDate;

/**
 * Filter to search based on date added
 */
public class DateAddedCriteria implements SearchCriteria {
    private final LocalDate thresholdDate;
    
    /**
	 * Constructor to create a new date filter
	 * 
	 * @param daysAgo	The days ago to be searched
	 */
    public DateAddedCriteria(int daysAgo) {
        this.thresholdDate = LocalDate.now().minusDays(daysAgo);
    }
    
    /**
	 * Test method to find the date range in the contact
	 * 
	 * @param contact	The contact against which the filter is used
	 */
    @Override
    public boolean test(Contact contact) {
        return contact.getTimeStamp().toLocalDate().isAfter(thresholdDate) || 
               contact.getTimeStamp().toLocalDate().isEqual(thresholdDate);
    }
}