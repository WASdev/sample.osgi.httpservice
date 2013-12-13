sample.osgi.httpservice
=======================

This sample project combines the Equinox httpService with OSGi Applications on the WebSphere Liberty profile.

This readme file focusses on how to install and test the sample. Please see https://www.ibmdw.net/wasdev/docs/sample-osgi-httpservice for a broader overview of the sample and its purpose. 

The project comprises a user feature and two OSGi applications. The user feature provides the HttpService. Of the two 
OSGi Applications, one registers itself with the HttpService; the other is detected and registered by the user feature. 

Installation
This section covers: 
- Getting the projects into Eclipse
- Getting them to compile cleanly by downloading some missing bundles
- Testing the application on a WebSphere Liberty profile. 

Getting the projects into Eclipse. 
You will need:
- A recent copy of the Eclipse IDE. I'm using Juno SR2. The WebSphere Development Tools don't support Kepler as of July 12th 2013. (https://www.ibm.com/developerworks/community/forums/html/topic?id=0b0dff76-f833-4607-95e8-538a9ddaec75)
  See http://www.eclipse.org/downloads/
- For Git tools I'm using the GitHub client for Windows, and the eGIT Eclipse client, http://download.eclipse.org/egit/updates

1. Create a WebSphere Liberty Profile runtime. 
- Install the WebSphere Application Server Developer Tools ("WDT") into Eclipse: https://www.ibmdw.net/wasdev/downloads/
- Window > Preferences > Server > Runtime Environments > Add > WebSphere Application Server V8.5 Liberty Profile. 
  Download a new runtime or reuse an existing one. Mine is called the default 'WebSphere Application Server V8.5 Liberty Profile'. 

2. Add a new server 
- Create a new server: New > Server > WebSphere Application Server V8.5 Liberty Profile


3. Download the following bundles:
  - http://www.eclipse.org/downloads/download.php?file=/equinox/drops/R-3.7-201106131736/org.eclipse.equinox.http.servlet_1.1.200.v20110502.jar
  - http://www.eclipse.org/downloads/download.php?file=/equinox/drops/R-3.7.2-201202080800/org.eclipse.osgi.services_3.3.0.v20110513.jar

4. Load them into Eclipse: File > Import > OSGi Bundle or Fragment. Take the defaults, including the use of Liberty as the target runtime. 


5. git clone https://github.com/WASdev/sample.osgi.httpservice
   File > Import > Existing projects into workspace. Pull in:
  - httpService-1.0 - user feature
  - httpServiceWab  - bundle contained by the user feature. http requests go through here. 
  - redWeb          - application bundle
  - redWeb.app      - OSGi Applcation
  - greenWeb        - second application bundle
  - greenWeb.app    - second OSGi Application

6. Set up your server. I've not worked out how to check a server.xml into GitHub in a way that makes it easy to pull out. So, edit your server.xml and add the following features:
  wab-1.0
  usr:httpService-1.0

  Also add this stanza:
  ````
     <webContainer deferServletLoad="false"/>
  ````

7. Install the first application: right click redWeb.app > Run as > run on server

8. Start the server

9. Test the application! Visit http://localhost:9080/httpService/red

   Web browser displays, 'Hello World from RedServlet'

10. Test the second application! Vitit http://localhost:9080/httpService/greenWeb

   Web browser displays, 'Hello World from GreenServlet'


