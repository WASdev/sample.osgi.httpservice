package com.ibm.samples.osgi.httpservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.getWriter().println ("Hello World from RedServlet");
	}

}
