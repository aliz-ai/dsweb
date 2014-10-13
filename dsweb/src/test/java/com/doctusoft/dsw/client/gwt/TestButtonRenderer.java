
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Button;
import com.xedge.jquery.client.JQuery;

public class TestButtonRenderer extends AbstractDswebTest {
	
	@Test
	public void testCaption() {
		final String initialCaption = "test";
		Button button = new Button( initialCaption ).withId( "button" );
		registerApp( button );
		assertEquals( initialCaption, JQuery.select( "#button" ).text() );
		String changedCaption = "new";
		button.getModel().setCaption( changedCaption );
		assertEquals( changedCaption, JQuery.select( "#button" ).text() );
	}
	
	@Test
	public void testWithoutIcon() {
		registerApp(new Button().withId("button"));
		assertEquals(0, JQuery.select("#button>i").length());
	}

	@Test
	public void testIcon() {
		registerApp(new Button().withIcon(BootstrapIcon.ICON_BOOK).withId("button"));
		assertEquals(BootstrapIcon.ICON_BOOK.getClassName(), JQuery.select("#button>i").attr("class"));
	}
	
	@Test
	public void testWhitespaceAdded() {
		registerApp(new Button("Caption").withIcon(BootstrapIcon.ICON_BOOK).withId("button"));
		assertTrue(JQuery.select("#button").text().startsWith(" "));
	}

	@Test
	public void testIconRemoved() {
		Button button = new Button().withIcon(BootstrapIcon.ICON_BOOK).withId("button");
		registerApp(button);
		assertEquals(BootstrapIcon.ICON_BOOK.getClassName(), JQuery.select("#button>i").attr("class"));
		button.withIcon(null);
		assertEquals(0, JQuery.select("#button>i").length());
	}

	@Test
	public void testIconChanged() {
		Button button = new Button().withIcon(BootstrapIcon.ICON_BOOK).withId("button");
		registerApp(button);
		assertEquals(BootstrapIcon.ICON_BOOK.getClassName(), JQuery.select("#button>i").attr("class"));
		button.withIcon(BootstrapIcon.ICON_BELL);
		assertEquals(BootstrapIcon.ICON_BELL.getClassName(), JQuery.select("#button>i").attr("class"));
	}

	@Test
	public void testIconWithNullCpation() {
		registerApp(new Button().withIcon(BootstrapIcon.ICON_BOOK).withId("button"));
		assertFalse(JQuery.select("#button").text().contains("null"));
	}
}
