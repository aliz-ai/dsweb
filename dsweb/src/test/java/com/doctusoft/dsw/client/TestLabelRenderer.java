package com.doctusoft.dsw.client;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestLabelRenderer {
	
	@Test
	public void testLabelRenderer() throws Exception {
		Server server = new Server(8080);        
		 
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(TestWebResourceServlet.class, "/");
        context.setWelcomeFiles(new String [] { "test-index.html" });
//        context.addServlet(new ServletHolder(new DumpServlet()),"/dump/*");
         
        server.start();
        
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://localhost:8080/test-index.html");
        System.out.println(page.getTitleText());
        
        server.stop();
	}

}
