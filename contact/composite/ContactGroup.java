package com.seveneleven.mycontactapp.contact.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.seveneleven.mycontactapp.contact.tag.Tag;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * Class to represent a group of contact components
 */
public class ContactGroup implements ContactComponent{
	private final List<ContactComponent> components = new ArrayList<>();
	
	/**
	 * Add a contact component to the component list
	 * 
	 * @param component	The component to be added
	 */
	public void addComponent(ContactComponent component) {
		components.add(component);
	}
	
	/**
	 * Get the size of the group
	 * 
	 * @return	The size of the component group
	 */
	public int getSize() {
		return components.size();
	}
	
	/**
	 * Bulk add the tags to the contacts
	 * 
	 * @param tag	The tag to add
	 */
	@Override
	public void addTag(Tag tag) {
		components.forEach(c -> c.addTag(tag));
	}
	
	/**
	 * Command to export the contacts to a CSV File
	 */
	@Override
	public String exportToCSV() {
		return components.stream()
						 .map(ContactComponent::exportToCSV)
						 .collect(Collectors.joining("\n"));
	}
	
	/**
	 * Command to soft delete contacts in bulk
	 */
	@Override
	public void performBulkSoftDelete(User actveUser){
		new ArrayList<>(components).forEach(c -> c.performBulkSoftDelete(actveUser));
	}
}
