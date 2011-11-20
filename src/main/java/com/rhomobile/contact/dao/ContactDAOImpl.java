package com.rhomobile.contact.dao;

import java.util.List;

import com.rhomobile.contact.form.Contact;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Contact> listContact() {
		return sessionFactory.getCurrentSession().createQuery("from Contact").list();
	}

    public Contact getContact(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);    	
		return contact;
    }

	public int addContact(Contact contact) {
		int id = (Integer)sessionFactory.getCurrentSession().save(contact);
		return id;
	}

	public void updateContact(Contact contact) {
		sessionFactory.getCurrentSession().merge(contact);
	}

	public void removeContact(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().load(Contact.class, id);
		if (null != contact) {
			sessionFactory.getCurrentSession().delete(contact);
		}
	}

}
