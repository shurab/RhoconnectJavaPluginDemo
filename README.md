RhoconnectJavaPluginDemo
===

Sample of complete Java back-end application with rhoconnect-java plugin using Spring 3.0 MVC as front end technology and Hibernate 
as back-end ORM technology. For this application Maven2 is also used for build and dependency management and 
embedded in-memory database (HSQL) to persist the data.
 
This sample is based on [RhoconnectJavaSample](https://github.com/shurab/RhoconnectJavaSample) application 
and  [Roconnect Java](https://github.com/rhomobile/rhoconnect-java) plugin.


Prerequisites:

* Java (1.6)
* Maven
* Git
* [rhoconnect-java](https://s3.amazonaws.com/rhoconnect-java/rhoconnect-java-1.0.2.jar) RhoConnect Java plugin jar
* [rhoconnect-java-api](https://s3.amazonaws.com/rhoconnect-java/rhoconnect-java-api-1.0.0.jar) RhoConnect Java API jar
* [Rhoconnect application](http://docs.rhomobile.com/rhoconnect/tutorial)
* [Rhodes application](http://docs.rhomobile.com/rhodes/tutorial)

## Getting started

To run demo application you need to clone it to you PC:

    :::term 
    $ git clone git@github.com:shurab/RhoconnectJavaPluginDemo.git
    
and tweak it.
    
Default configuration of demo application assumes that all components (rhoconnect app, rhodes app, and demo app) are running on the the same host. 
To run it in your environment you should edit the following settings:

* add the rhoconnect-java plugin jar file to your local Maven repository 
* add the rhoconnect-java-api jar file to your local Maven repository 
* edit rhoconnect server's api_token (property `apiToken` in WEB-INF/spring-servlet.xml file)
* edit partition string according your preferences (com/rhomobile/contact/service/ContactServiceImpl.java file)

### Adding the rhoconnect-java plugin to your Maven project

At this moment rhoconnect-java and rhoconnect-java-api are not available in Maven public repositories and you need install them manually into your Maven's local repository.
Download [rhoconnect-java](https://s3.amazonaws.com/rhoconnect-java/rhoconnect-java-1.0.2.jar) and [rhoconnect-java-api](https://s3.amazonaws.com/rhoconnect-java/rhoconnect-java-api-1.0.0.jar) files  
and put them into your hard drive, and issue the following Maven's commands:

    :::term
	$ mvn install:install-file -Dfile=/path-to-jar/rhoconnect-java-api-1.0.0.jar -DgroupId=com.msi.rhoconnect.api \
	  -DartifactId=rhoconnect-java-api -Dversion=1.0.0 -Dpackaging=jar
	$ mvn install:install-file -Dfile=/path-to-jar/rhoconnect-java-1.0.2.jar \
	  -DgroupId=com.rhomobile.rhoconnect  -DartifactId=rhoconnect-java -Dversion=1.0.2 -Dpackaging=jar
	
### Creating RhoConnect application and editing "api_token"

Create RhoConnect sync application, if you wanted use custom "api_token":

    :::term 
    $ rhoconnect app syncserver
    $ cd syncserver; bundle install

Edit settings/settings.yml by adding to `development` section `api_token` key/value pair. Value is a matter of your choice :-): 

    :development:
       ...
       :api_token: my-rhoconnect-token

And set the same `api_token` value for property `apiToken` in WEB-INF/spring-servlet.xml file:

    <bean id="rhoconnectClient" class = "com.rhomobile.rhoconnect.RhoconnectClient" init-method="setAppEndpoint" >
        <!-- -->
	    <property name="apiToken" value="my-rhoconnect-token" />	
    </bean>
    
In the simplest case you need only start 'vanilla' rhoconnect app:

	:::term 
	$ redis-cli flushdb
	$ rhoconnect start

 
### Editing partitioning

If you want your data to be partitioned by your login name (user name), then edit in com.rhomobile.contact/ContactAuthenticate file return value of `authenticate` method.  Bu default, application partitioned by ‘app’ (the data will be shared among all users).

### Creating Rhodes application 

Steps, required to create rhodes application:

    :::term 
    $ rhodes app contact-app
    $ cd contact-app
    $ rhodes model contact lastname,firstname,email,telephone

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

### Synchronizing data between java back-end and rhodes mobile application

Now you are ready to synchronize java back-end data with your rhodes application.
In the 1st terminal launch rhoconnect server:

    :::term 
    $ rake rhoconnect:start
	
In 2d terminal window start  demo application:
	
    :::term 
    $ mvn jetty:run
	
If you correctly adjusted demo configurations, then in 1st terminal window you see logging output 
that java backend application successfully registered by rhoconect server:
	
    :::term
    ... 
    127.0.0.1 - - [21/Nov/2011 12:31:53] "POST /api/source/save_adapter HTTP/1.1" 200 30 0.0026
    ...

Another way to check configuration is to login to rhoconnect web console.
Rhoconnect application exposes a web interface which runs on `http://localhost:9292/console/` by default.
If you login to the console, then on `Backend App URL` page value of `backend app url` should be `http://localhost:8080/contacts` 

And finally, in 3d terminal window start either rhodes iPhone sumilator:
	
    :::term
    $ rake run:iphone:rhosimulator
	
or Android simulator:
	
    :::term
    $ rake run:rhosimulator
	


	    
