package com.seveneleven.mycontactapp.contact.tag;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory class to create predefined tags
 */
public class TagFactory {
	private static final Map<String, Tag> tagPool = new HashMap<>();
	
	/**
	 * Load tags into the tagPool
	 */
	static {
		EnumSet<PredefinedTag> defaultTags = EnumSet.allOf(PredefinedTag.class);
		for(PredefinedTag tag : defaultTags) {
			tagPool.put(tag.name(), new Tag(tag.name()));
		}
	}
	
	/**
	 * Method to get a specific tag from the tagPool
	 * 
	 * @param name	The name of the tag to get
	 * @return	The Tag
	 */
	public static Tag getTag(String name) {
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Tag cannot be empty");
		}
		
		String normalizedName = name.trim().toUpperCase();
		
		return tagPool.computeIfAbsent(normalizedName, Tag::new);
	}
	
	/**
	 * Method to get all available tags in tagPool
	 * 
	 * @return	The list of all available tags in tagPool
	 */
	public static Collection<Tag> getAllAvailableTags() {
		return tagPool.values();
	}
}
