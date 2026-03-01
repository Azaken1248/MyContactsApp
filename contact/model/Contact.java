package com.seveneleven.mycontactapp.contact.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.seveneleven.mycontactapp.contact.composite.ContactComponent;
import com.seveneleven.mycontactapp.contact.tag.Tag;
import com.seveneleven.mycontactapp.contact.tag.TagAssignment;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * Abstract class that represents a contact 
 */
public abstract class Contact implements ContactComponent{
	private final UUID id;
	private final LocalDateTime createdAt;
	
	private String name;
	
	private final List<PhoneNumber> phoneNumbers;
	private final List<EmailAddress> emailAddresses;
	private final Set<TagAssignment> tagAssignments;
	
	
	/**
	 * The constructor to create the contact object
	 * 
	 * @param name	The name of the contact
	 */
	protected Contact(String name) {
		this.id = UUID.randomUUID();
		this.createdAt = LocalDateTime.now();
		this.name = name;
		this.phoneNumbers = new ArrayList<>();
		this.emailAddresses = new ArrayList<>();
		this.tagAssignments = new HashSet<>();
	}
	
	/**
	 * Copy Constructor for deep copying.
	 * 
	 * @param source	The source contact
	 */
	protected Contact(Contact source) {
		this.id = source.getId();
		this.createdAt = source.getTimeStamp();
		this.name = source.getName();
		
		// Defensive copy the lists
		this.phoneNumbers = new ArrayList<>(source.getPhoneNumbers());
		this.emailAddresses = new ArrayList<>(source.getEmailAddresses());
		this.tagAssignments = new HashSet<>();
		for(Tag tag : source.getTags()) {
			this.addTag(tag);
		}
	}
	
	/**
	 * Method to get the contact id
	 * 
	 * @return	The UUID of the contact (UUID)
	 */
	public UUID getId() { return id; }
	
	/**
	 * Method to return the exact time the contact was created
	 * 
	 * @return	The TimeStamp of the contact (LocalDateTime)
	 */
	public LocalDateTime getTimeStamp() { return createdAt; }
	
	/**
	 * Method to get the name of the contact
	 * 
	 * @return	The name of the contact (String)
	 */
	public String getName() { return name; }
	
	/**
	 * Method to set the name
	 * @param name	The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method to get the list of the phone numbers
	 * 
	 * @return	The list of the phone numbers (List\<PhoneNumber\>)
	 */
	public List<PhoneNumber> getPhoneNumbers() { return phoneNumbers; }
	
	/**
	 * Method to get the list of the email addresses
	 * 
	 * @return	The list of email addresses (List\<EmailAddress\>)
	 */
	public List<EmailAddress> getEmailAddresses() { return emailAddresses; }
	
	/**
	 * Method to get the list of tags associated with the contacts
	 * 
	 * @return	The list of tags (List\<Tag\>)
	 */
	public java.util.Set<Tag> getTags() { 
        return tagAssignments.stream()
                .map(TagAssignment::getTag)
                .collect(java.util.stream.Collectors.toSet());
    }
	
	/**
	 * Method to add a phone number
	 * 
	 * @param phoneNumber	Phone number to be added
	 */
	public void addPhoneNumber(PhoneNumber phoneNumber) {
		phoneNumbers.add(phoneNumber);
	}
	
	/**
	 * Method to add a email address
	 * 
	 * @param emailAddress	Email address to be added
	 */
	public void addEmailAddress(EmailAddress emailAddress) {
		emailAddresses.add(emailAddress);
	}
	
	/**
	 * Method to remove a phone number
	 * 
	 * @param index	The index of the phone number to remove
	 */
    public void removePhoneNumber(int index) {
        if (index >= 0 && index < phoneNumbers.size()) {
            this.phoneNumbers.remove(index);
        } else {
            throw new IllegalArgumentException("Invalid phone number index.");
        }
    }
    
    /**
     * Mthod to remove email addresses
     * 
     * @param index	The index of the email addresses to remove
     */
    public void removeEmailAddress(int index) {
        if (index >= 0 && index < emailAddresses.size()) {
            this.emailAddresses.remove(index);
        } else {
            throw new IllegalArgumentException("Invalid email index.");
        }
    }
    
    /**
     * Clear all nested components on nested del
     */
    public void cascadeDelete() {
    	this.phoneNumbers.clear();
    	this.emailAddresses.clear();
    }
    
    /**
     * Method to add tags to the contact
     * 
     * @param tag	The tag to be added
     */
    @Override
    public void addTag(Tag tag) {
        TagAssignment assignment = new TagAssignment(this, tag);
        
        if (this.tagAssignments.add(assignment)) { 
            tag.addContactLink(this);
        }
    }
    
    /**
     * Method to remove tags linked to the contact
     * @param tag	The tag to be removed
     */
	public void removeTag(Tag tag) {
        TagAssignment assignment = 
            new TagAssignment(this, tag);
        
        if (this.tagAssignments.remove(assignment)) { 
            tag.removeContactLink(this);
        }
    }
    
    /**
     * Method to export contacts to CSV
     * 
     * @param activeUser The current logged in user
     */
    @Override
    public abstract String exportToCSV() ;
    
    /**
     * Method to perform a bulk delete
     * 
     * @param activeUser	The current user logged in
     */
    @Override
    public void performBulkSoftDelete(User activeUser) {
    	activeUser.getContacts().remove(this);
    	activeUser.getRecycleBin().add(this);
    }
	/**
	 * Abstract method to display a summary of all contact details
	 * 
	 * @return	The Summary of the contact (String)
	 */
	public abstract String getContactSummary();
	
	/**
	 * Abstract method to get the type of contact
	 * 
	 * @return	The Summary of the contact (String)
	 */
	public abstract String getContactType();
	
	/**
	 * Method for deep copy implementation
	 * 
	 * @return	The copy of the contact
	 */
	public abstract Contact copy();
}
