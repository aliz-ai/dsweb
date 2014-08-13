package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Label;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestBaseComponentRenderer extends AbstractDswebTest {
	
	@Test
	public void testId() {
		registerApp(new Label().withId("label"));
		assertEquals(1, JQuery.select("#label").length());
	}
	
	@Test
	public void testVisibleInitialTrue() {
		registerApp(new Label().withVisible(true).withId("label"));
		new Timer() {
			@Override
			public void run() {
				assertTrue(!"none".equals(JQuery.select("#label").css("display")));
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}

	@Test
	public void testVisibleInitialFalse() {
		registerApp(new Label().withVisible(false).withId("label"));
		new Timer() {
			@Override
			public void run() {
				assertTrue("none".equals(JQuery.select("#label").css("display")));
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}
	
	@Test
	public void testVisibilityHide() {
		Label label = new Label().withId("label");
		registerApp(label);
		label.getModel().setVisible(false);
		assertTrue("none".equals(JQuery.select("#label").css("display")));
	}

	@Test
	public void testVisibilityShow() {
		final Label label = new Label().withVisible(false).withId("label");
		registerApp(label);
		new Timer() {
			@Override
			public void run() {
				assertTrue("none".equals(JQuery.select("#label").css("display")));
				label.getModel().setVisible(true);
				assertTrue(!"none".equals(JQuery.select("#label").css("display")));
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}
	
	@Test
	public void testStyleClasses() {
		Label label = new Label().withStyleClasses("a", "b").withId("label");
		registerApp(label);
		assertTrue(JQuery.select("#label").hasClass("a"));
		assertTrue(JQuery.select("#label").hasClass("b"));
		assertFalse(JQuery.select("#label").hasClass("c"));
		// add another
		label.addStyleClass("c");
		assertTrue(JQuery.select("#label").hasClass("c"));
		// remove one
		label.removeStyleClass("b");
		assertFalse(JQuery.select("#label").hasClass("b"));
	}

}
