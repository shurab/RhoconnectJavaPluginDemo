package com.rhomobile.contact.dao;

import java.util.List;

import com.rhomobile.contact.form.Contact;

public interface ContactDAO {
	
	public int addContact(Contact contact);
	public List<Contact> listContact();
	public void removeContact(Integer id);
    public void updateContact(Contact contact);
    public Contact getContact(Integer id);

}
