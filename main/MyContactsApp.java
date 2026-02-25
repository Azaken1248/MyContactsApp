package com.seveneleven.mycontactapp.main;
import java.util.Scanner;

import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.model.UserBuilder;
import com.seveneleven.mycontactapp.user.model.UserProfile;
import com.seveneleven.mycontactapp.user.model.UserProfileBuilder;
import com.seveneleven.mycontactapp.user.utilities.PasswordHasher;
import com.seveneleven.mycontactapp.user.validation.InvalidEmailException;
import com.seveneleven.mycontactapp.user.validation.InvalidPhoneNumberException;
import com.seveneleven.mycontactapp.user.validation.UserValidator;
import com.seveneleven.mycontactapp.user.validation.WeakPasswordException;

/**
 * Main class that serves as the entry point to the my contacts application.
 * 
 * @author rsin3607
 * @version 1.0
 */
public class MyContactsApp {
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final PasswordHasher hasher = new PasswordHasher();
	
	private static User loggedInUser = null;
	
	public static void performRegistration() {
		scanner.nextLine();
		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		
		System.out.print("Enter Password: ");
		String password = scanner.nextLine();
		
		System.out.print("Enter User Type: ");
		String type = scanner.nextLine();
		
		System.out.print("Enter Username: ");
		String username = scanner.nextLine();
		
		System.out.print("Enter Bio/Status: ");
		String bio = scanner.nextLine();
		
		System.out.print("Enter Phone Number: ");
		String phoneNumber = scanner.nextLine();
		try {
			UserValidator.validateEmail(email);
			UserValidator.validatePassword(password);
			UserValidator.validatePhoneNumber(phoneNumber);
			
			String hashedPassword = hasher.hash(password);
			
			UserProfile newProfile = new UserProfileBuilder().setUsername(username)
												  .setBio(bio)
												  .setPhoneNumber(phoneNumber)
												  .build();
			
			User newUser = new UserBuilder().setEmail(email)
											.setPasswordHash(hashedPassword)
											.setProfileInfo(newProfile)
											.setUserType(type.toUpperCase())
											.build();
			
			System.out.println("------User Registered-------");
			System.out.println("Email: " + newUser.getEmail());
			System.out.println("Password Hash: " + newUser.getPasswordHash());
			System.out.println("Profile Information: " + newUser.getProfileInfo().toString());
			System.out.println("Type: " + newUser.getAccountTier());
			
		}catch (InvalidEmailException | WeakPasswordException | InvalidPhoneNumberException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("Unexpected error occured: " + e.getMessage());
		}
		
		
		
		
	}
	
	/**
	 * Handles the menu before the user is logged in
	 * 
	 * @return user intent as a boolean
	 */
	public static boolean handleGuestMenu() {
		System.out.println("---Guest Menu---");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("0. Exit");
		System.out.println("----------------");
		System.out.print("Enter choice: ");
		
		int choice = scanner.nextInt();
		
		
		return switch(choice) {
			case 1 -> {
				performRegistration();
				yield true;
			}
			case 2 -> {
				// TODO: Implement Authentication and login
				System.out.println("Login will be implemented soon");
				yield true;
			}
			
			case 0 -> {
				System.out.println("Thank you!!");
				yield false;
			}
			
			default -> {
				System.out.println("Please enter a valid choice");
				yield true;
			}
		};
	}
	
	/**
	 * Main method that starts the main application loop
	 * @param args
	 */
	public static void main(String[]args) {
		System.out.println("==================Welcome to MyContactsApp==================");
		
		boolean isRunning = true;
		
		while(isRunning) {
			if(loggedInUser == null) {
				isRunning = handleGuestMenu();
			}
		}
		
	}
}
