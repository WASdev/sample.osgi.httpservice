package com.ibm.samples.osgi.httpservice;

import java.util.logging.Logger;
import java.util.logging.Level;

import javax.servlet.ServletException;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

public class RedBean {

	String _owner;
	private static final Logger _logger = Logger.getLogger(RedBean.class.getName());
	
	public void setOwner (String name) { 
		System.out.println ("RedBean owner set to " + name);
	}
	
	public void setHttpService (HttpService httpService) { 
		RedServlet rs = new RedServlet();
		try { 
			httpService.registerServlet("/red",rs, null, null);
		} catch (ServletException se) { 
			_logger.log(Level.SEVERE, "RedBean unable to register servlet", se);
		} catch (NamespaceException e) {
			_logger.log(Level.SEVERE, "RedBean unable to register servlet", e);		
		}
		System.out.println ("RedServlet registered at /red");
	}
}
