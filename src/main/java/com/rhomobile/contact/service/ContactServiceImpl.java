package com.rhomobile.contact.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhomobile.contact.dao.ContactDAO;
import com.rhomobile.contact.form.Contact;

import com.rhomobile.rhoconnect.RhoconnectClient;
import com.rhomobile.rhoconnect.RhoconnectResource;

@Service
public class ContactServiceImpl implements ContactService, RhoconnectResource {
    @Autowired
    private ContactDAO contactDAO;
    @Autowired
    private RhoconnectClient client;

    private static final String sourceName  = "Contact"; // name of DAO model

    @Transactional
    public int addContact(Contact contact) {
        int id = contactDAO.addContact(contact);
        client.notifyOnCreate(sourceName, Integer.toString(id), contact);
        return id;
    }

    @Transactional
    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
        client.notifyOnUpdate(sourceName, Integer.toString(contact.getId()), contact);
    }

    @Transactional
    public void removeContact(Integer id) {
        contactDAO.removeContact(id);
        client.notifyOnDelete(sourceName, Integer.toString(id));
    }

    @Transactional
    public List<Contact> listContact() {
        return contactDAO.listContact();
    }

    @Transactional
    public Contact getContact(Integer id) {
        return contactDAO.getContact(id);
    }

    @Override
    @Transactional
    public Map<String, Object> rhoconnectQuery(String partition) {
        Map<String, Object> h = new HashMap<String, Object>();
        List<Contact> contacts =  listContact();
        
        Iterator<Contact> it = contacts.iterator( );
        while(it.hasNext()) {
            Contact c =(Contact)it.next();
            h.put(c.getId().toString(), c);
        }
        return h;
    }

    @Override
    @Transactional
    public Integer rhoconnectCreate(String partition, Map<String, Object> attributes) {
        Contact contact = new Contact();
        try {
            BeanUtils.populate(contact, attributes);
            int id = addContact(contact);
            return id;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
	public Integer rhoconnectUpdate(String partition, Map<String, Object> attributes) {
        Integer id = Integer.parseInt((String)attributes.get("id"));
        Contact contact = getContact(id);
        try {
            BeanUtils.populate(contact, attributes);
            updateContact(contact);
            return id;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Integer rhoconnetDelete(String partition, Map<String, Object> attributes) {
        String objId = (String)attributes.get("id");
        Integer id = Integer.parseInt(objId);
        removeContact(id);       
        return id;
    }

 }
