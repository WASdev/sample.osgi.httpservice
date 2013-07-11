sample.osgi.httpservice
=======================

Introduction
This sample project combines the Exquinox httpServiceth OSGi Applications on the WebSphere Liberty profile. 

The first version of the project comprises a simple OSGi Application that registers a Servlet with the httpService. The httpService itself is provided by a user feature.

Installation
This section covers: 
- Getting the projects into Eclipse
- Getting them to compile cleanly by downloading some missing bundles
- Testing the application on a WebSphere Liberty profile. 

Getting the projects into Eclipse. 
You will need:
- A recent copy of the Eclipse IDE. I'm using Juno SR2. See http://www.eclipse.org/downloads/
- For Git tools I'm using the GitHub client for Windows, and the eGIT Eclipse client, http://download.eclipse.org/egit/updates

Use these or similar tools to pull the code down and create four projects in Eclipse:
- httpService-1.0 - user feature
- httpServiceWab  - bundle contained by the user feature. http requests go through here. 
- redWeb          - application bundle
- redWeb.app      - OSGi Applcation

To get to a clean workspace we need to download and install two bundles from the Internet, and create a Liberty server to run the application on. 
1. Download the following bundles:
   - http://www.eclipse.org/downloads/download.php?file=/equinox/drops/R-3.7-201106131736/org.eclipse.equinox.http.servlet_1.1.200.v20110502.jar
   - http://www.eclipse.org/downloads/download.php?file=/equinox/drops/R-3.7.2-201202080800/org.eclipse.osgi.services_3.3.0.v20110513.jar

Load them into Eclipse: File > Import > OSGi Bundle or Fragment

2. Create a WebSphere Liberty Profile runtime. 
- Install the WebSphere Application Server Developer Tools ("WDT") into Eclipse: https://www.ibmdw.net/wasdev/downloads/
- Create a new runtime: New > Server > WebSphere Application Server V8.5 Liberty Profile
- Project > Clean may be required to clean up any remaining red 'x's . 

3. Run and test!
- TO BE CONTINUED
