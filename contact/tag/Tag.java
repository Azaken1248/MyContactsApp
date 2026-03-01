package com.seveneleven.mycontactapp.contact.tag;

import java.util.Objects;

/**
 * Tag class to represent a tag object
 */
public class Tag {
	private final String name;
	
	/**
	 * Contstructor to set tag name
	 * 
	 * @param name	The name of the tag
	 */
	public Tag(String name) {
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Tag name cannot be empty");
		}
		
		this.name = name.trim().toUpperCase();
	}
	
	/**
	 * Method to get the name of the tag
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Override equals to compare tag equality
	 * 
	 * @param Object Any java object
	 * @return true or false based on equality (boolean)
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Tag tag = (Tag) o;
		
		return name.equals(tag.name);
	}
	
	/**
	 * Generate a hash code for this class
	 * 
	 * @return the hashcode for the class
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
