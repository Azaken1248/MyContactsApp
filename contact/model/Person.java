package com.seveneleven.mycontactapp.contact.model;

import java.util.ArrayList;
import java.util.List;

import com.seveneleven.mycontactapp.contact.tag.Tag;


/**
 * The class that represents a person type of contact
 */
public class Person extends Contact {

	private String relationship;

	/**
	 * Construtor to create a new Person object using the person builder
	 * 
	 * @param builder	The person builder
	 */
	protected Person(PersonBuilder builder) {
		super(builder.name);
		this.relationship = builder.relationship;

		for(PhoneNumber phoneNumber : builder.phones) {
			this.addPhoneNumber(phoneNumber);
		}

		for(EmailAddress emailAddress : builder.emails) {
			this.addEmailAddress(emailAddress);
		}
	}

	/**
	 * Copy constructor
	 * 
	 * @param source	The source contact
	 */
	public Person(Person source) {
		super(source);
		this.relationship = source.relationship;
	}

	/**
	 * Method to get the relationship of the contact
	 * 
	 * @return	The relationship of the contact (String)
	 */
	public String getRelationship() { return relationship; }

	/**
	 * Method to set the relationship
	 * @param relationship	The relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	/**
	 * Method to display a summary of all contact details
	 * 
	 * @return	The Summary of the contact (String)
	 */
	@Override
	public String getContactSummary() { 
		StringBuilder contactDetailsBuilder = new StringBuilder().append(getName())
				.append("[")
				.append(relationship)
				.append("]");
		for(Tag tag : tags) {
			contactDetailsBuilder.append("[")
			.append(tag.getName())
			.append("]");
		}

		return contactDetailsBuilder.toString();

	}

	/**
	 * Method to get the type of contact
	 * 
	 * @return	The type of contact (String)
	 */
	@Override
	public String getContactType() { return "PERSON"; }

	/**
	 * method to copy the contact
	 * 
	 * @return Contact	the copied contact
	 */
	@Override
	public Contact copy() {
		return new Person(this);
	}

	/**
	 * Method to export the current contact row to a CSV String
	 * 
	 * @param activeUser	The current logged in user
	 */
	@Override
	public String exportToCSV(){
		StringBuilder phoneStrBuilder = new StringBuilder();
		for(PhoneNumber phoneNumber : getPhoneNumbers()) {
			phoneStrBuilder.append(phoneNumber.toString())
			.append("|");
		}

		StringBuilder emailStrBuilder = new StringBuilder();
		for(EmailAddress emailAddress : getEmailAddresses()) {
			emailStrBuilder.append(emailAddress.toString())
			.append("|");
		}

		return String.format("%s,%s,%s,%s,%s,N/A,N/A,%s,%s", getId(),
				getContactType(),
				getTimeStamp().toLocalDate(),
				getName(),
				relationship,
				phoneStrBuilder.toString(),
				emailStrBuilder.toString());
	}


	/**
	 * Inner builder class to build a person object
	 */
	public static class PersonBuilder {
		String name;
		String relationship = "Aquaintance";

		private final List<PhoneNumber> phones = new ArrayList<>();
		private final List<EmailAddress> emails = new ArrayList<>();

		/**
		 * Method to set the name of the instance
		 * 
		 * @param name	The name to be set
		 * 
		 * @return	The PersonBuilder instance
		 */
		public PersonBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Method to set the relationship of the instance
		 * 
		 * @param name	The relation to be set
		 * 
		 * @return	The PersonBuilder instance
		 */
		public PersonBuilder setRelationsip(String relationship) {
			this.relationship = relationship;
			return this;
		}

		/**
		 * Method to add the phone numbers to the person
		 * 
		 * @param name	The number to be added
		 * 
		 * @return	The PersonBuilder instance
		 */
		public PersonBuilder addPhoneNumber(PhoneNumber phoneNumber) {
			phones.add(phoneNumber);
			return this;
		}

		/**
		 * Method to add the emails to the person
		 * 
		 * @param email	The email to be added
		 * 
		 * @return	The PersonBuilder instance
		 */
		public PersonBuilder addEmailAddress(EmailAddress emailAddress) {
			emails.add(emailAddress);
			return this;
		}

		/**
		 * The method to build the person object
		 * 
		 * @return the person object
		 */
		public Person build() {
			return ContactFactory.createPersonContact(this);
		}
	}

}
