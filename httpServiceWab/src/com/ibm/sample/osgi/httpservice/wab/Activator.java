package com.ibm.sample.osgi.httpservice.wab;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println ("Wab feature bundle activated");

	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
