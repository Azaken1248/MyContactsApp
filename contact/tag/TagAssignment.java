package com.seveneleven.mycontactapp.contact.tag;

import com.seveneleven.mycontactapp.contact.model.Contact;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class to assign tags to contacts
 */
public class TagAssignment {
    private final Contact contact;
    private final Tag tag;
    private final LocalDateTime assignedAt;
    
    /**
     * Constructor to make a new Tag <-> Contact association
     * 
     * @param contact	The contact to associate
     * @param tag	The tag to associate
     */
    public TagAssignment(Contact contact, Tag tag) {
        this.contact = contact;
        this.tag = tag;
        this.assignedAt = LocalDateTime.now();
    }
    
    /**
     * Method to get the tag
     * 
     * @return	The tag
     */
    public Tag getTag() { return tag; }
    
    /**
     * Method to get the contact
     * 
     * @return The contact
     */
    public Contact getContact() { return contact; }
    
	/**
	 * Method to get the time of assignment
	 *  
	 * @return	The time of assinment
	 */
    public LocalDateTime getAssignedAt() { return assignedAt; }
    
    /**
     * Overriding Equals
     * 
     * @param Object to test
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagAssignment that = (TagAssignment) o;
        return contact.equals(that.contact) && tag.equals(that.tag);
    }

    /**
     * Method to get the hash code of the object
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(contact, tag);
    }
}