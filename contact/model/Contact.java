package com.seveneleven.mycontactapp.contact.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Abstract class that represents a contact 
 */
public abstract class Contact {
	private final UUID id;
	private final LocalDateTime createdAt;
	
	private String name;
	
	private final List<PhoneNumber> phoneNumbers;
	private final List<EmailAddress> emailAddresses;
	
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
