package com.doctusoft.dsw.client;

import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.xedge.jquery.client.JQuery;

/**
 * To run a test in eclipse, make sure that the project has GWT enabled in the project settings, then
 * use Run As -> GWT JUnit Test. This creates a run configuration, if it begins to start up, stop it first.
 * Go to the run configuration edit the JVM arguments to add:
 *   -javaagent:c:\Users\Gábor\.m2\repository\com\doctusoft\lombok-ds\0.4.0\lombok-ds-0.4.0.jar=ECJ
 *   (with your home directory and the actual lombok version)  
 */
public abstract class AbstractDswebTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "com.doctusoft.dsw.DswTest";
	}

	protected void registerApp(HasComponentModel app) {
		RendererFactory<JQuery> factory = GWT.create(RendererFactory.class);
		// remove all previous children
		JQuery.select("body").children().remove();
		factory.getRenderer(app.getComponentModel()).getWidget().appendTo("body");
	}
	
	//useful for debugging purposes
	protected void dumpRoot() {
		JQuery root = JQuery.select( ":root" );
		System.out.println( root.html() );
		System.out.println( "--------------------------------------------------------" );
	}
}
