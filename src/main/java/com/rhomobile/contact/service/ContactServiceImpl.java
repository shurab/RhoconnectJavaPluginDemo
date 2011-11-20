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
    //private static final Logger logger = Logger.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactDAO contactDAO;
    @Autowired
    private RhoconnectClient client;

    private static final String sourceName  = "Contact"; // name of DAO model

    @Transactional
    public int addContact(Contact contact) {
        int id = contactDAO.addContact(contact);
        String partition  = getPartition(); //contactResource.getPartition();
        client.notifyOnCreate(sourceName, partition, Integer.toString(id), contact);
        return id;
    }

    @Transactional
    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
        String partition  = getPartition(); //contactResource.getPartition();
        client.notifyOnUpdate(sourceName, partition, Integer.toString(contact.getId()), contact);       
    }

    @Transactional
    public void removeContact(Integer id) {
        contactDAO.removeContact(id);
        String partition  = getPartition(); //contactResource.getPartition();
        client.notifyOnDelete(sourceName, partition, Integer.toString(id));
    }

    @Transactional
    public List<Contact> listContact() {
        return contactDAO.listContact();
    }

    @Transactional
    public Contact getContact(Integer id) {
        return contactDAO.getContact(id);
    }

    // TODO    
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

	@Override
	public String getPartition() {
        // Data partitioning: i.e. your user name for filtering data on per user basis 
		return "alexb";
	}

 }
