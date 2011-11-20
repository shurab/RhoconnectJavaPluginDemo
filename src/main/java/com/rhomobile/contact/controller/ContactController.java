package com.rhomobile.contact.controller;

import java.util.Map;

import org.apache.log4j.Logger;

import com.rhomobile.contact.form.Contact;
import com.rhomobile.contact.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	private static final Logger logger = Logger.getLogger(ContactController.class);	

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String listContacts(Map<String, Object> map) {
		map.put("contactList", contactService.listContact());
		logger.info("ContactController::index");
		return "index";
	}

	@RequestMapping("/new")
	public String newContact(Map<String, Object> map) {
		map.put("contact", new Contact());
		logger.info("ContactController::new");
		return "new";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
	Contact contact, BindingResult result) {
		if(contact.getId() == null) {
			int id = contactService.addContact(contact);
			logger.info("Create new contact with id = " + Integer.toString(id));
		} else {
			contactService.updateContact(contact);
			logger.info("Update contact with id = " + contact.getId());
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteContact(@PathVariable("id")
	Integer id) {
		contactService.removeContact(id);
		logger.info("Delete contact with id = " + id);	
		return "redirect:/index";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public ModelAndView showContact(@PathVariable("id")
	Integer id) {
		Contact contact = contactService.getContact(id);

		ModelAndView mav = new ModelAndView();		
        // Use the view named "show" to display the data
        mav.setViewName("show");
        // Add a model object to be displayed by the view
        mav.addObject("contact", contact);
        return mav;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView editContact(@PathVariable("id")
	Integer id, Contact contact) {
		contact = contactService.getContact(id);

		ModelAndView mav = new ModelAndView();		
        // Use the view named "edit" to display the data
        mav.setViewName("edit");
        // Add a model object to be displayed by the view
        mav.addObject("contact", contact);
        return mav;
	}

}
