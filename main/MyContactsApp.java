package com.seveneleven.mycontactapp.main;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.seveneleven.mycontactapp.auth.Authentication;

import com.seveneleven.mycontactapp.auth.providers.AuthProvider;

import com.seveneleven.mycontactapp.auth.session.SessionManager;
import com.seveneleven.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.seveneleven.mycontactapp.auth.strategy.OAuthStrategy;
import com.seveneleven.mycontactapp.user.command.ChangePasswordCommand;
import com.seveneleven.mycontactapp.user.command.ProfileCommand;
import com.seveneleven.mycontactapp.user.command.ProfileUpdateController;
import com.seveneleven.mycontactapp.user.command.UpdateBioCommand;
import com.seveneleven.mycontactapp.user.command.UpdatePhoneNumberCommand;
import com.seveneleven.mycontactapp.user.command.UpdateTierCommand;
import com.seveneleven.mycontactapp.user.command.UpdateUserNameCommand;

import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.model.UserBuilder;
import com.seveneleven.mycontactapp.user.model.UserProfile;
import com.seveneleven.mycontactapp.user.model.UserProfileBuilder;

import com.seveneleven.mycontactapp.user.storage.UserFileManager;

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
	private static final Map<String, User> userDatabase = UserFileManager.loadData();
	
	/**
	 * Register the user to the application
	 */
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
			
			userDatabase.put(newUser.getEmail(), newUser);
			UserFileManager.saveData(userDatabase);
			
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
	 * Log in the user to the application
	 */
	public static void performLogin() {
		System.out.println("\n---Login---");
		
		scanner.nextLine();
		
		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		
		User userLoginAttempt = userDatabase.get(email);
		
		if(userLoginAttempt == null) {
			System.out.println("User not found. Please register first!!");
			return;
		}
		
		boolean isPremium = "PREMIUM".equalsIgnoreCase(userLoginAttempt.getAccountTier());
		
		System.out.println("Select Auth method: ");
		System.out.println("1. Password");
		
		if(isPremium) {
			System.out.println("2. OAuth Token (AuthProvider) [Unlocked]");
		}else {
			System.out.println("2. OAuth Token (AuthProvider) [Locked - Premium only]");
		}
		
		
		System.out.print("Choice: ");
		String method = scanner.nextLine();
		
		Authentication authStrategy;
		String secret;
		
		if("2".equals(method)) {
			if(!isPremium) {
				System.out.println("You must be a premium user to access OAuth services. Please use password instead.");
				return;
			}
			
			System.out.println("\n[Redirecting to AuthProvider...]");
			String generatedToken = AuthProvider.generateToken(email);
			System.out.println("[AuthProvider] Authentication Successful. Your token is " + generatedToken);
			System.out.println("[Redirecting back to MyContactsApp.....]");
			
			authStrategy = new OAuthStrategy(userDatabase);
			
			System.out.print("Please paste your OAuth Token to finalize login: ");
			secret = scanner.nextLine();
		}else {
			authStrategy = new BasicAuthStrategy(userDatabase, hasher);
			
			System.out.print("Enter password: ");
			secret = scanner.nextLine();
		}
		
		Optional<User> loginResult = authStrategy.authenticate(email, secret);
		
		if(loginResult.isPresent()) {
			SessionManager.getInstance().loginUser(loginResult.get());
			System.out.println("Login Successful");
		}else {
			System.out.println("Login Failed: Please enter valid credentials");
		}
		
	}
	
	/**
	 * Handles the menu before the user is logged in
	 * 
	 * @return user intent as a boolean
	 */
	public static boolean handleGuestMenu() {
		System.out.println("\n---Guest Menu---");
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
				performLogin();
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
	
	public static void handleProfileMenu() {
		boolean inProfileMenu = true;
		
		while(inProfileMenu) {
			User activeUser = SessionManager.getInstance().getCurrentUser().get();
			UserProfile profile = activeUser.getProfileInfo();
			
			System.out.println("---Profile Management---");
			System.out.println("Username: " + profile.getUsername());
			System.out.println("Bio: " + profile.getBio());
			System.out.println("Bio: " + profile.getPhoneNumber());
			System.out.println("Current Tier: " + activeUser.getAccountTier());
			
			if(profile.getAadharNumber() != null) {
				System.out.println("Linked Aadhar: " + profile.getAadharNumber());
				System.out.println("Linked Bank: " + profile.getBankDetails());
			}
			System.out.println("------------------------");
			System.out.println("1. Update username");
			System.out.println("2. Update bio/status");
			System.out.println("3. Update phone number");
			System.out.println("4. Change password");
			System.out.println("5. Upgrade to PREMIUM");
			System.out.println("6. Undo last action");
			System.out.println("0. Back to user dashboard");
			System.out.print("Enter choice: ");
			
			String input = scanner.nextLine();
			
			inProfileMenu = switch(input) {
				case "1" -> {
					System.out.print("Enter new username: ");
					String newName = scanner.nextLine();
					
					ProfileCommand nameCmd = new UpdateUserNameCommand(activeUser.getProfileInfo(), newName);
					ProfileUpdateController.executeCommand(nameCmd);
					
					UserFileManager.saveData(userDatabase);
					yield true;
				}
				case "2" -> {
					System.out.print("Enter new bio: ");
					String newBio = scanner.nextLine();
					
					ProfileCommand bioCmd = new UpdateBioCommand(activeUser.getProfileInfo(), newBio);
					ProfileUpdateController.executeCommand(bioCmd);
					
					UserFileManager.saveData(userDatabase);
					yield true;
				}
				case "3" -> {
					System.out.print("Enter new phone number: ");
					String newPhoneNumber = scanner.nextLine();
					
					try {
						UserValidator.validatePhoneNumber(newPhoneNumber);
					} catch (InvalidPhoneNumberException e) {
						System.out.println(e.getMessage());
					}
					
					ProfileCommand phoneCmd = new UpdatePhoneNumberCommand(activeUser.getProfileInfo(), newPhoneNumber);
					ProfileUpdateController.executeCommand(phoneCmd);
					
					UserFileManager.saveData(userDatabase);
					yield true;
				}
				case "4" -> {
					System.out.print("Enter new password: ");
					String newPassword = scanner.nextLine();
					
					ProfileCommand passCmd = new ChangePasswordCommand(activeUser, newPassword, hasher);
					ProfileUpdateController.executeCommand(passCmd);
					
					UserFileManager.saveData(userDatabase);
					yield true;
				}
				case "5" -> {
					ProfileCommand tierCmd = new UpdateTierCommand(userDatabase, activeUser);
					ProfileUpdateController.executeCommand(tierCmd);
					
					UserFileManager.saveData(userDatabase);
					yield true;
				}
				case "6" -> {
					ProfileUpdateController.undoCommand();
					
					UserFileManager.saveData(userDatabase);
					yield true;
				}
				case "0" -> {
					System.out.println("Returning to dashboard...");
					yield false;
				}
				default -> {
					System.out.println("Invalid Choice!!");
					yield true;
				}
			}
;		}
	}
	
	/**
	 * Handles the menu after the user is logged in
	 * 
	 * @return user intent as a boolean
	 */
	public static boolean handleUserMenu() {
		User activeUser = SessionManager.getInstance().getCurrentUser().get();
		
		System.out.println("\n---Main Menu (Logged in as " + activeUser.getEmail() +")---");
		System.out.println("1. Profile Management");
		System.out.println("2. Contact Management");
		System.out.println("0. logout");

		System.out.print("Enter Choice: ");
		int input = scanner.nextInt();
		
		return switch(input) {
			case 1 -> {
				handleProfileMenu();
				yield true;
			}
			case 2 -> {
				System.out.println("To be implemented");
				yield true;
			}
			case 0 -> {
				System.out.println("Logging out...");
				SessionManager.getInstance().logoutUser();
				yield true;
			}
			default -> {
				System.out.println("Invalid Choice!!");
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
			if(!SessionManager.getInstance().isLoggedIn()) {
				isRunning = handleGuestMenu();
			}else {
				isRunning = handleUserMenu();
			}
		}
		
	}
}
