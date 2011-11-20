package com.rhomobile.contact.service;

import java.util.List;

import com.rhomobile.contact.form.Contact;

public interface ContactService {
	
	public int addContact(Contact contact);
	public void updateContact(Contact contact);
	public List<Contact> listContact();
	public void removeContact(Integer id);
	public Contact getContact(Integer id);

}
