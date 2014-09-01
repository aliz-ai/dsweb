package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.PasswordField;
import com.xedge.jquery.client.JQuery;

public class TestPasswordFieldRenderer extends AbstractDswebTest {

	@Test
	public void testInputIsPasswordField() {
		PasswordField passwordField = new PasswordField();
		passwordField.withId("password");
		registerApp(passwordField);
		assertEquals("password", JQuery.select("#password").attr("type"));
	}
	
	@Test
	public void testValueChangeByServerSide() {
		String password = "pass";
		PasswordField passwordField = new PasswordField();
		passwordField.withId("password");
		registerApp(passwordField);
		assertEquals("", JQuery.select("#password").val());
		passwordField.getModel().setValue(password);
		assertEquals(password, JQuery.select("#password").val());
	}
	
	@Test
	public void testValueChangeByClientSide() {
		String password = "pass";
		PasswordField passwordField = new PasswordField();
		passwordField.withId("password");
		registerApp(passwordField);
		JQuery jqueryPass = JQuery.select("#password");
		assertEquals("", jqueryPass.val());
		jqueryPass.val(password);
		jqueryPass.change();
		assertEquals(password, passwordField.getModel().getValue());
	}
}
