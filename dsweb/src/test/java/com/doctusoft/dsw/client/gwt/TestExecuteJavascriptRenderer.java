package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.ExecuteJavascript;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestExecuteJavascriptRenderer extends AbstractDswebTest {
	
	@Test
	public void testExecuteBeforeRendered() {
		registerApp(new ExecuteJavascript()
			.execute("window.xx = 'helloworld'")
			.execute("window.xx = 'changed'"));
		assertEquals("changed", JQuery.selectWindow().prop("xx"));
	}
	
	@Test
	public void testExecuteAfterRendered() {
		ExecuteJavascript executeJavascript = new ExecuteJavascript();
		registerApp(executeJavascript);
		assertEquals(null, JQuery.selectWindow().prop("xx"));
		executeJavascript
			.execute("window.xx = 'helloworld'")
			.execute("window.xx = 'changed'");
		assertEquals("changed", JQuery.selectWindow().prop("xx"));
	}

	@Test
	public void testListCleared() {
		final ExecuteJavascript executeJavascript = new ExecuteJavascript();
		registerApp(executeJavascript);
		executeJavascript
			.execute("window.xx = 'helloworld'")
			.execute("window.xx = 'changed'");
		delayTestFinish(100);
		new Timer() {
			@Override
			public void run() {
				assertEquals(0, executeJavascript.getModel().getSnippets().size());
				finishTest();
			}
		}.schedule(5);
	}
}
