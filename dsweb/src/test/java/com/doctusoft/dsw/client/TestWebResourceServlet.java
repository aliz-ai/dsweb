package com.doctusoft.dsw.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.ByteStreams;

public class TestWebResourceServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.startsWith("/")) {
			uri = uri.substring(1);
		}
		if (uri.endsWith(".html")) {
			resp.setContentType("text/html");
			ByteStreams.copy(getClass().getClassLoader().getResourceAsStream(uri), resp.getOutputStream());
		}
	}

}
