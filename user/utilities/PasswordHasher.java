package com.seveneleven.mycontactapp.user.utilities;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is responsible for the hashing of the user password before storing it.
 */
public class PasswordHasher {

	/**
	 * Creates a hash of the password entered
	 * 
	 * @param password The password to be hashed
	 * @return the hashed password string. (String)
	 * @throws RuntimeException if the pattern is not found
	 */
	public String hash(String password) throws RuntimeException {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hashBytes = digest.digest(password.getBytes());
			
			StringBuilder hexString = new StringBuilder();
			for(byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			
			return hexString.toString();
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Algorithm for hashing not found");
		}
	}
}
