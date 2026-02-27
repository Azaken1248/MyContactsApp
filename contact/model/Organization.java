package com.seveneleven.mycontactapp.contact.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that represents an organization type of contact
 */
public class Organization extends Contact {
	
	private String website = "N/A";
	private String industry = "General Business";
	
	/**
	 * Construtor to create a new Organization object using the organization builder
	 * 
	 * @param builder	The organization builder
	 */
	protected Organization(OrganizationBuilder builder) {
		super(builder.name);
		this.website = builder.website;
		this.industry = builder.industry;
		
		for(PhoneNumber phoneNumber : builder.phones) {
			this.addPhoneNumber(phoneNumber);
		}
		
		for(EmailAddress emailAddress : builder.emails) {
			this.addEmailAddress(emailAddress);
		}
	}
	
	/**
	 * Method to get the website of the contact
	 * 
	 * @return	The website of the contact (String)
	 */
	public String getWebsite() { return website; }
	
	/**
	 * Method to get the industry of the contact
	 * 
	 * @return	The industry of the contact (String)
	 */
	public String getIndustry() { return industry; }
	
	/**
	 * Method to display a summary of all contact details
	 * 
	 * @return	The Summary of the contact (String)
	 */
	@Override
	public String getContactSummary() { return getName() + "[" + industry + "|" + website + "]"; }
	
	/**
	 * Method to get the type of contact
	 * 
	 * @return	The Summary of the contact (String)
	 */
	@Override
	public String getContactType() { return "ORGANIZATION"; }
	
	/**
	 * Inner builder class to build a organization object
	 */
	public static class OrganizationBuilder {
		String name;
		String website = "N/A";
		String industry = "General Business";
		
		private final List<PhoneNumber> phones = new ArrayList<>();
		private final List<EmailAddress> emails = new ArrayList<>();
		
		/**
		 * Method to set the name of the instance
		 * 
		 * @param name	The name to be set
		 * 
		 * @return	The OrganizationBuilder instance
		 */
		public OrganizationBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		/**
		 * Method to set the website of the instance
		 * 
		 * @param name	The website link to be set
		 * 
		 * @return	The OrganizationBuilder instance
		 */
		public OrganizationBuilder setWebsite(String website) {
			this.website = website;
			return this;
		}
		
		/**
		 * Method to set the industry of the instance
		 * 
		 * @param name	The industry to be set
		 * 
		 * @return	The OrganizationBuilder instance
		 */
		public OrganizationBuilder setIndustry(String industry) {
			this.industry = industry;
			return this;
		}
		
		/**
		 * Method to add the phone numbers to the organization
		 * 
		 * @param name	The number to be added
		 * 
		 * @return	The OrganizationBuilder instance
		 */
		public OrganizationBuilder addPhoneNumber(PhoneNumber phoneNumber) {
			phones.add(phoneNumber);
			return this;
		}
		
		/**
		 * Method to add the emails to the organization
		 * 
		 * @param email	The email to be added
		 * 
		 * @return	The OrganizationBuilder instance
		 */
		public OrganizationBuilder addEmailAddress(EmailAddress emailAddress) {
			emails.add(emailAddress);
			return this;
		}
		
		/**
		 * The method to build the organization object
		 * 
		 * @return the organization object
		 */
		public Organization build() {
			return ContactFactory.createOrganizationContact(this);
		}
	}

}
