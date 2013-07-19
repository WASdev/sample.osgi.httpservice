sample.osgi.httpservice
=======================

Introduction
This sample project combines the Equinox httpServic with OSGi Applications on the WebSphere Liberty profile. 

The first version of the project comprises a simple OSGi Application that registers a Servlet with the httpService. The httpService itself is provided by a user feature.

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

6. Set up your server. I've not worked out how to check a server.xml into GitHub in a way that makes it easy to pull out. So, edit your server.xml and add the following features:
  wab-1.0
  usr:httpService-1.0

7. Install the application: right click redWeb.app > Run as > run on server

8. Start the server

9. Test the application! Visit http://localhost:9080/httpService/red

Web browser displays, 'Hello World from RedServlet'

Console output from server:
Launching defaultServer (WebSphere Application Server 8.5.5.0/wlp-1.0.3.20130510-0831) on IBM J9 VM, version ...
[AUDIT   ] CWWKE0001I: The server defaultServer has been launched.
Wab feature bundle activated
[AUDIT   ] CWWKZ0058I: Monitoring dropins for applications. 
[AUDIT   ] CWWKT0016I: Web application available (default_host): http://localhost:9080/httpService/
[AUDIT   ] CWWKZ0001I: Application redWeb.app started in 2.716 seconds.
[AUDIT   ] CWWKF0011I: The server defaultServer is ready to run a smarter planet.
RedBean owner set to mark
RedServlet registered at /red


