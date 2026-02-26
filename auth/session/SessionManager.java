package com.seveneleven.mycontactapp.auth.session;

import java.util.Optional;

import com.seveneleven.mycontactapp.user.model.User;

public class SessionManager {
	
	private User currentUser;
	private static SessionManager instance;
	
	/**
	 * private constructor to prevent rouge sessions.
	 */
	private SessionManager() {
		this.currentUser = null;
	}
	
	
	/**
	 * creates a single session if there is no other session ongoing.
	 * 
	 * @return An instance of the session 
	 */
	public static SessionManager getInstance() {
		if(instance == null) {
			instance = new SessionManager();
		}
		
		return instance;
	}
	
	/**
	 * To login the user to the session.
	 * 
	 * @param user
	 */
	public void loginUser(User user) {
		this.currentUser = user;
	}
	
	/**
	 * To logout the user to the session.
	 */
	public void logoutUser() {
		this.currentUser = null;
	}
	
	/**
	 * Method to check if a user is logged in.
	 * 
	 * @return	True if logged in else false
	 */
	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	/**
	 * A method to get the current user that is logged in.
	 * 
	 * @return	The User Wrapped in an Optional
	 */
	public Optional<User> getCurrentUser(){
		return Optional.ofNullable(currentUser);
	}
}
