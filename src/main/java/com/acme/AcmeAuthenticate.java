package com.acme;

import java.util.Map;
import org.apache.log4j.Logger;
import com.rhomobile.rhoconnect.Rhoconnect;

public class AcmeAuthenticate implements Rhoconnect {
	private static final Logger logger = Logger.getLogger(AcmeAuthenticate.class);	
	
	@Override
	public boolean authenticate(String login, String password, Map<String, Object> attributes) {
		logger.info("AcmeAuthenticate#authenticate: implement your authentication code!");    		
        // TODO: your authentication code goes here ...
		return true;
	}

}
