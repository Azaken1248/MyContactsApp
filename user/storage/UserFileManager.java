package com.seveneleven.mycontactapp.user.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.model.UserBuilder;
import com.seveneleven.mycontactapp.user.model.UserProfile;
import com.seveneleven.mycontactapp.user.model.UserProfileBuilder;

/**
 * This class is responsible for the utility functions to save
 * user data and load user data from a persistant file based 
 * storage system.
 */
public class UserFileManager {
	// File path to store user data
	private static final String FILE_PATH  = "./src/com/seveneleven/mycontactapp/user/storage/user_data.txt";
	
	// File Object
	private static File file = new File(FILE_PATH);
	
	/**
	 * Saves the userData from map to file
	 * 
	 * @param userDatabase	The map to load data from.
	 */
	public static void saveData(Map<String, User> userDatabase) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
			for(User user: userDatabase.values()) {
				UserProfile profile = user.getProfileInfo();
				
				String data = String.format("%s|%s|%s|%s|%s|%s",
											user.getEmail(),
											user.getPasswordHash(),
											user.getAccountTier(),
											profile.getUsername(),
											profile.getBio(),
											profile.getPhoneNumber(),
											profile.getAadharNumber() != null ? profile.getAadharNumber() : "",
											profile.getBankDetails() != null ? profile.getBankDetails() : "");
				writer.write(data);
				writer.newLine();
			}
		}catch(IOException e) {
			System.out.println("Unable to save users: " + e.getMessage());
		}
	}
	
	/**
	 * Loads the user data from the file into the map
	 * 
	 * @return	The userDatabase Map
	 */
	public static Map<String, User> loadData(){
		Map<String, User> loadedUsers = new HashMap<>();
		
		if(!file.exists()) {
			return loadedUsers;
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|", -1);
				
				if(parts.length >= 7) {
					String email = parts[0];
					String passwordHash = parts[1];
					String accountType = parts[2];
					String username = parts[3];
					String bio = parts[4];
					String phoneNumber = parts[5];
					String aadharNumber = parts[6];
					String bankDetails = parts[7];
					
					UserProfile profile = new UserProfileBuilder().setUsername(username)
																  .setBio(bio)
																  .setPhoneNumber(phoneNumber)
																  .build();
					profile.setAadharNumber(aadharNumber);
					profile.setBankDetails(bankDetails);
					
					User user = new UserBuilder().setEmail(email)
												 .setPasswordHash(passwordHash)
												 .setProfileInfo(profile)
												 .setUserType(accountType)
												 .build();
					
					loadedUsers.put(email, user);
				}
			}
			
		}catch(IOException e) {
			System.out.println("Unable to load users: " + e.getMessage());
		}
		
		return loadedUsers;
	}
}
