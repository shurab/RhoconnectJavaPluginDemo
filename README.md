RhoconnectJavaPluginDemo
===

Sample of complete Java back-end application with rhoconnect-java plugin using Spring 3.0 MVC as front end technology and Hibernate 
as back-end ORM technology. For this application Maven2 is also used for build and dependency management and 
embedded in-memory database (HSQL) to persist the data.
 
This sample is based on [RhoconnectJavaSample](https://github.com/shurab/RhoconnectJavaSample) application 
and  [Roconnect Java](https://github.com/rhomobile/rhoconnect-java) plugin.


Prerequisites:

* Java (1.6)
* Maven2 (2.2.1)
* Git
* [Rhoconncect-java](https://github.com/downloads/rhomobile/rhoconnect-java/rhoconnect-java-1.0-SNAPSHOT.jar) plugin jar
* [Rhoconnect application](http://docs.rhomobile.com/rhoconnect/tutorial)
* [Rhodes application](http://docs.rhomobile.com/rhodes/tutorial)

## Gettring started

To run demo application you need to clone it to you PC:

    :::term 
    git clone git@github.com:shurab/RhoconnectJavaPluginDemo.git
    
and tweak it.
    
Default configuration of demo application assumes that all components (rhoconnect app, rhodes app, and demo app) are running on the the same host. To run it in your environment you should edit the following settings:

* set absolute path to hoconnect-java jar file (tag `<systemPath>` in pom.xml file) 
* edit rhoconnect server's api_token (property `apiToken` in WEB-INF/spring-servlet.xml file)
* edit partition string according your preferences (com/rhomobile/contact/service/ContactServiceImpl.java file)

Steps, required to create rhodes application:

    :::term 
	rhodes app contact-app
	cd contact-app
    rhodes model contact lastname,firstname,email,telephone

Edit rhoconfig.txt file

	:::term 
	...
	syncserver = 'http://localhost:9292/application'
	...

Edit app/index.erb

	:::term 
	...
	<li><a href="Contact">Contacts</a></li>
	...

Edit app/Contact/contact.rb
	
	:::term 
	...
	enable :sync
	...

Now you are ready to synchronize java back-end data with your rhodes application.
In the 1st terminal launch rhoconnect server:

	:::term 
	rake rhoconnect:start
	
In 2d terminal window start  demo application:
	
	:::term 
	mvn jetty:run
	
If you correctly adjusted demo configurations, then in 1st terminal window you see logging output 
that java backend application successfully registered by rhoconect server:
	
	:::term
	... 
	127.0.0.1 - - [21/Nov/2011 12:31:53] "POST /api/source/save_adapter HTTP/1.1" 200 30 0.0026
	...

Another way to check configuration is to login to rhoconnect web console.
Rhoconnect application exposes a web interface which runs on `http://localhost:9292/console/` by default.
If you login to the console, then on `Backend App URL` page value of `backend app url` should be `http://localhost:8080/contacts` 

And finally, in 3d terminal window start rhodes application:
	
	:::term
	rake run:iphone:rhosimulator
	    