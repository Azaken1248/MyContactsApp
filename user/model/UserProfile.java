package com.seveneleven.mycontactapp.user.model;

/**
 * Represents and displays the personal information of the user
 */
public class UserProfile {
	private String username;
	private String bio;
	private String phoneNumber;
	
	// To be populated with OAuth
	private String aadharNumber;
	private String bankDetails;
	
	/**
	 * Protected constructor to enforce object creation only via the UserProfileBuilder.
	 * 
	 * @param builder the UserProfileBuilder containing the configured user data.
	 */
	protected UserProfile(UserProfileBuilder builder) {
		this.username = builder.getUsername();
		this.bio = builder.getBio();
		this.phoneNumber = builder.getPhoneNumber();
	}
	
	/**
	 * Get the display name of the user.
	 * 
	 * @return The user's display name. (String)
	 */
	public String getUsername() { return username; }
	
	/**
	 * Get the bio of the user.
	 * 
	 * @return The user's bio. (String)
	 */
	public String getBio() { return bio; }
	
	/**
	 * Get the phone number of the user.
	 * 
	 * @return The user's phone number. (String)
	 */
	public String getPhoneNumber() { return phoneNumber; }
	
	/**
	 * Get the Aadhar number of the user.
	 * 
	 * @return	The user's Aadhar number (String)
	 */
	public String getAadharNumber() { return aadharNumber; }
	
	/**
	 * Get the bank details of the user.
	 * 
	 * @return The user's BankDetails
	 */
	public String getBankDetails() { return bankDetails; }
	
	/**
	 * Set the Aadhar number of the user
	 * 
	 * @param aadharNumber	Aadhar number of the user
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	
	/**
	 * Set the bank details of the user
	 * 
	 * @param aadharNumber	Bank Details of the user
	 */
	public void setBankDetails(String bankDetails) {
		this.bankDetails = bankDetails;
	}
	
	/**
	 * Set the display name of the user
	 * 
	 * @param username	Username of user
	 */
	public void setUsername(String username) throws IllegalArgumentException{
		if(username == null || username.trim().isEmpty()) {
			throw new IllegalArgumentException("The username cannot be empty!!");
		}
		
		this.username = username;
	}
	
	/**
	 * Set the bio of the user
	 * 
	 * @param bio	Bio of the user
	 */
	public void setBio(String bio) throws IllegalArgumentException{
		if(bio == null || bio.trim().isEmpty()) {
			throw new IllegalArgumentException("The bio cannot be empty!!");
		}
		this.bio = bio;
	}
	
	/**
	 * Set the phone number of the user
	 * 
	 * @param phoneNumber	Phone Number of the user
	 */
	public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException{
		if(phoneNumber == null || phoneNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("The phone number cannot be empty!!");
		}
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Get the string representation of the user profile.
	 * 
	 * @return The UserProfile object. (String)
	 */
	@Override
	public String toString() {
		return String.join("|", username, bio, phoneNumber);
	}
}
