package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.PasswordField;
import com.xedge.jquery.client.JQuery;

public class TestPasswordFieldRenderer extends AbstractDswebTest {

	@Test
	public void testPasswordFieldIsPresent() {
		PasswordField passwordField = new PasswordField();
		passwordField.withId("password");
		registerApp(passwordField);
		assertEquals(1, JQuery.select("#password").length());
	}
	
	@Test
	public void testValueChange() {
		String password = "pass";
		PasswordField passwordField = new PasswordField();
		passwordField.withId("password");
		registerApp(passwordField);
		assertEquals("", JQuery.select("#password").val());
		passwordField.getModel().setValue(password);
		assertEquals(password, JQuery.select("#password").val());
	}
	
}
