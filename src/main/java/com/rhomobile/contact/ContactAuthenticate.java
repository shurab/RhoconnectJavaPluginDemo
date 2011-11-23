package com.rhomobile.contact;

import java.util.Map;
import org.apache.log4j.Logger;
import com.rhomobile.rhoconnect.Rhoconnect;

public class ContactAuthenticate implements Rhoconnect {
	private static final Logger logger = Logger.getLogger(ContactAuthenticate.class);	
	
	@Override
	public boolean authenticate(String login, String password, Map<String, Object> attributes) {
		logger.info("ContactAuthenticate#authenticate: implement your authentication code!");    		
        // TODO: your authentication code goes here ...
		return true;
	}
}
