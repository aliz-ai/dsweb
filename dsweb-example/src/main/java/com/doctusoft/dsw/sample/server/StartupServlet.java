package com.doctusoft.dsw.sample.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.doctusoft.dsw.sample.server.person.Person;
import com.googlecode.objectify.ObjectifyService;

public class StartupServlet extends HttpServlet {
	

	@Override
	public void init() throws ServletException {
		super.init();
		ObjectifyService.register(Person.class);
	}
}
