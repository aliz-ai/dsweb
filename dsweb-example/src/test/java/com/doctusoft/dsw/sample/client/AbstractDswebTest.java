package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.xedge.jquery.client.JQuery;

public abstract class AbstractDswebTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "com.doctusoft.dsw.sample.MvpExampleTest";
	}

	protected void registerApp(HasComponentModel app) {
		RendererFactory<JQuery> factory = GWT.create(RendererFactory.class);
		factory.getRenderer(app.getComponentModel()).getWidget().appendTo("body");
	}
}
