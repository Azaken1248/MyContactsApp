package com.seveneleven.mycontactapp.contact.storage;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.seveneleven.mycontactapp.contact.composite.ContactComponent;
import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.model.EmailAddress;
import com.seveneleven.mycontactapp.contact.model.Organization;
import com.seveneleven.mycontactapp.contact.model.Person;
import com.seveneleven.mycontactapp.contact.model.PhoneNumber;
import com.seveneleven.mycontactapp.contact.tag.Tag;
import com.seveneleven.mycontactapp.contact.tag.TagFactory;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * Class to manage storage and retrieval of data from the file storage system.
 */
public class ContactFileManager {

	//Base storage directory
	private static final String BASE_DIR = "./src/com/seveneleven/mycontactapp/contact/storage/";

	/**
	 * Method to save contact to the file
	 * @param user	The user who's contact is to be saved
	 */
	public static void saveContacts(User user) {
		File file_dir = generateFile(user);

		if(!file_dir.exists()) {
			file_dir.mkdirs();
		}

		File contactFile = new File(file_dir + "/contacts.txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(contactFile))){
			for(Contact contact : user.getContacts()) {

				StringBuilder phoneStr = new StringBuilder();
				for(PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
					phoneStr.append(phoneNumber.getLabel())
					.append(",")
					.append(phoneNumber.getPhoneNumber())
					.append(";");
				}

				StringBuilder emailStr = new StringBuilder();
				for(EmailAddress emailAddress : contact.getEmailAddresses()) {
					emailStr.append(emailAddress.getLabel())
					.append(",")
					.append(emailAddress.getEmail())
					.append(";");
				}

				// ✅ NEW: Pack the Tags using comma separation
				StringBuilder tagStr = new StringBuilder();
				for(Tag tag : contact.getTags()) {
					tagStr.append(tag.getName()).append(",");
				}
				String finalTagStr = tagStr.length() > 0 ? tagStr.substring(0, tagStr.length() - 1) : "N/A";

				String line = "";
				if(contact.getContactType().equalsIgnoreCase("PERSON")) {
					if(contact instanceof Person) {
						Person p = (Person) contact;
						// ✅ Added %s at the end for finalTagStr
						line = String.format("PERSON|%s|%s|%s|%s|%s",p.getName(),
								p.getRelationship(),
								phoneStr,
								emailStr,
								finalTagStr);
					}
				}else {
					if(contact instanceof Organization) {
						Organization o = (Organization) contact;
						// ✅ Added %s at the end for finalTagStr
						line = String.format("ORGANIZATION|%s|%s|%s|%s|%s|%s",o.getName(),
								o.getWebsite(),
								o.getIndustry(),
								phoneStr,
								emailStr,
								finalTagStr);
					}
				}
				writer.write(line);
				writer.newLine();
			}

		}catch(IOException e) {
			System.out.println("Error Saving Data: " + e.getMessage());
		}
	}

	/**
	 * Method to load user contacts from the file storage
	 * * @param user	The user for which the contacts are loaded
	 */
	public static void loadContacts(User user) {
		File file_dir = generateFile(user);
		File contactFile = new File(file_dir + "/contacts.txt");

		try(BufferedReader reader = new BufferedReader(new FileReader(contactFile))) {
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|", -1);

				String type = parts[0];

				int phoneIdx = type.equalsIgnoreCase("PERSON") ? 3 : 4;
				int emailIdx = type.equalsIgnoreCase("PERSON") ? 4 : 5;
				// ✅ NEW: Calculate where the tag column should be
				int tagIdx = type.equalsIgnoreCase("PERSON") ? 5 : 6; 

				List<PhoneNumber> phoneNumbers = parsePhoneString(parts[phoneIdx]);
				List<EmailAddress> emailAddresses = parseEmailString(parts[emailIdx]);

				Contact loadedContact;

				if(type.equalsIgnoreCase("PERSON")) {
					loadedContact = new Person.PersonBuilder().setName(parts[1])
							.setRelationsip(parts[2])
							.build();

				}else {
					loadedContact = new Organization.OrganizationBuilder().setName(parts[1])
							.setWebsite(parts[2])
							.setIndustry(parts[3])
							.build();
				}

				for(PhoneNumber phoneNumber : phoneNumbers) {
					loadedContact.addPhoneNumber(phoneNumber);
				}

				for(EmailAddress emailAddress : emailAddresses) {
					loadedContact.addEmailAddress(emailAddress);
				}

				// ✅ NEW (BACKWARD COMPATIBLE): Only process tags if the column exists in the save file
				if (parts.length > tagIdx) {
					String tagsString = parts[tagIdx];
					if (!tagsString.equals("N/A") && !tagsString.trim().isEmpty()) {
						String[] tagNames = tagsString.split(",");
						for (String tName : tagNames) {
							if (!tName.trim().isEmpty()) {
								// Rebuilds the Flyweight and Association links
								loadedContact.addTag(TagFactory.getTag(tName.trim()));
							}
						}
					}
				}

				user.getContacts().add(loadedContact);
			}
		}catch(IOException e) {
			System.out.println("No saved data found!!");
		}
	}

	public static void saveExportFile(User activeUser, ContactComponent component) {
		String BASE_DIR = "./src/com/seveneleven/mycontactapp/contact/storage/";
		String safeEmail = activeUser.getEmail().replace("@", "_").replace(".", "_");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String timeStamp = LocalDateTime.now().format(formatter);

		String path = new StringBuilder().append(BASE_DIR)
				.append(safeEmail)
				.append("/exports/export_")
				.append(activeUser.getProfileInfo().getUsername())
				.append(timeStamp)
				.append(".csv")
				.toString();

		File exportFile = new File(path);

		if(exportFile.getParentFile() != null && !exportFile.getParentFile().exists()) {
			exportFile.getParentFile().mkdirs();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportFile))){
			 String headers = new StringBuilder().append("ID,")
						 .append("Type,")
					     .append("Created At,")
					     .append("Name,")
						 .append("Relationship,")
						 .append("Website,")
						 .append("Industry,")
					     .append("Phone Numbers,")
					     .append("Emails,")
					     .append("Tags") // ✅ Added Tags column to CSV header
					     .toString();
			 
			 writer.write(headers);
			 writer.newLine();
			 
			 writer.write(component.exportToCSV());
			 
			 System.out.println("File Saved to " + path);
			 
		} catch(IOException e) {
			System.out.println("Error Exporting Data: " + e.getMessage());
		}

	}
	
	/**
	 * Method to save the user tags in a file
	 * * @param activeUser	The current logged in user
	 */
    public static void saveUserTags(User activeUser) {
        String BASE_DIR = "./src/com/seveneleven/mycontactapp/contact/storage/";
        String safeEmail = activeUser.getEmail().replace("@", "_").replace(".", "_");
        File tagsFile = new File(BASE_DIR + safeEmail + "/tags.txt");

        if (tagsFile.getParentFile() != null && !tagsFile.getParentFile().exists()) {
            tagsFile.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new java.io.BufferedWriter(new FileWriter(tagsFile))) {
            for (Tag tag : TagFactory.getAllAvailableTags()) {
                writer.write(tag.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user tags: " + e.getMessage());
        }
    }
    
    /**
     * Method to load tags from the save file
     * * @param activeUser	The current logged in user
     */
    public static void loadUserTags(User activeUser) {
        String BASE_DIR = "./src/com/seveneleven/mycontactapp/contact/storage/";
        String safeEmail = activeUser.getEmail().replace("@", "_").replace(".", "_");
        File tagsFile = new java.io.File(BASE_DIR + safeEmail + "/tags.txt");

        if (!tagsFile.exists()) return; 

        try (BufferedReader reader = new java.io.BufferedReader(new FileReader(tagsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    TagFactory.getTag(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user tags: " + e.getMessage());
        }
    }

	/**
	 * Helper method to parse phone number objects
	 * * @param phoneStr	Phone number objects string
	 * * @return	The List of parsed phone numbers (List\<PhoneNumber\>)
	 */
	private static List<PhoneNumber> parsePhoneString(String phoneStr) {
		List<PhoneNumber> phoneNumbers = new ArrayList<>();

		if(phoneStr.isEmpty()) { return phoneNumbers; }

		String[] phones = phoneStr.split(";");

		for(String phone : phones) {
			String[] parts = phone.split(",");

			if(parts.length == 2) {
				String label = parts[0];
				String phoneNumber = parts[1];

				phoneNumbers.add(new PhoneNumber(label, phoneNumber));
			}
		}

		return phoneNumbers;
	}

	/**
	 * Helper method to parse the email objects
	 * * @param emailStr	Email objects string
	 * * @return	The List of parsed emails (List\<EmailAddress\>)
	 */
	private static List<EmailAddress> parseEmailString(String emailStr) {
		List<EmailAddress> emailAddresses = new ArrayList<>();

		if(emailStr.isEmpty()) { return emailAddresses; }

		String[] emails = emailStr.split(";");

		for(String email : emails) {
			String[] parts = email.split(",");

			if(parts.length == 2) {
				String label = parts[0];
				String emailAddress = parts[1];

				emailAddresses.add(new EmailAddress(label, emailAddress));
			}
		}

		return emailAddresses;
	}

	/**
	 * Helper method to get File path
	 * * @param user	The user who's directory is to be created
	 * @return	The file object pointing to that directory
	 */
	private static File generateFile(User user) {
		String safeEmail = user.getEmail().replace("@", "_").replace(".", "_");

		return new File(BASE_DIR + safeEmail);
	}
}