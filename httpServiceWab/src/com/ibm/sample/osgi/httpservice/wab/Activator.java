package com.ibm.sample.osgi.httpservice.wab;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


public class Activator implements BundleActivator {
	
	private static final Logger _logger = Logger.getLogger(Activator.class.getName());
	ServiceTracker<HttpService, HttpService> httpSvcTracker;

	@Override
	public void start(final BundleContext bc) throws Exception {
		System.out.println ("Wab feature bundle activated");
		
		// First track the HttpService
		System.out.println ("Activator looking for HttpService");
		httpSvcTracker = new ServiceTracker<HttpService, HttpService> 
			(bc, HttpService.class, new ServiceTrackerCustomizer<HttpService, HttpService>() 
			{
				@Override
				public HttpService addingService(ServiceReference<HttpService> arg0) 
				{
					return bc.getService(arg0);
				}

				@Override
				public void modifiedService(ServiceReference<HttpService> arg0, HttpService arg1) 
				{
					// no-op
				}

				@Override
				public void removedService(ServiceReference<HttpService> arg0, HttpService arg1) 
				{
					// no-op
				}
			});
		httpSvcTracker.open();
		
		// Let's track all Servlet services seen by the system bundle
		System.out.println ("Commence Servlet tracking");
		Bundle systemBundle = bc.getBundle(Constants.SYSTEM_BUNDLE_LOCATION);
		BundleContext systemBC = systemBundle.getBundleContext();
		ServiceTracker<Servlet, Servlet> st = new ServiceTracker<Servlet, Servlet> (systemBC, Servlet.class, new ServletTrackerCustomizer(systemBC)); 
		st.open();
		System.out.println ("Servlet tracking underway");
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}
	
	
	class ServletTrackerCustomizer implements ServiceTrackerCustomizer<Servlet, Servlet> {
		
		BundleContext bc;
		
		public ServletTrackerCustomizer (BundleContext ctx) { 
			bc = ctx;
		}

		@Override
		public Servlet addingService(ServiceReference<Servlet> sr) {
			String bundleName = sr.getBundle().getSymbolicName();
			System.out.println ("adding Servlet from bundle: " + bundleName);
			Servlet servlet = bc.getService(sr);
			
			HttpService httpSvc = httpSvcTracker.getService();
			try { 
				String path = "/" + bundleName;
				httpSvc.registerServlet(path, servlet, null, null);
				System.out.println ("Registered new Servlet with path " + path);
			} catch (ServletException se) { 
				_logger.log(Level.SEVERE, "RedBean unable to register servlet", se);
			} catch (NamespaceException e) {
				_logger.log(Level.SEVERE, "RedBean unable to register servlet", e);		
			}
			return servlet;
		}

		@Override
		public void modifiedService(ServiceReference<Servlet> sr, Servlet srv) {
			// nothing to do here
		}

		@Override
		public void removedService(ServiceReference<Servlet> sr, Servlet srv) {
			// We could unregister the Servlet from the HttpService in a  
		} 
		
	}

}
