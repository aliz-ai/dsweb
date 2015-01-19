package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.xedge.jquery.client.JQuery;

public class TestTabSheetRenderer extends AbstractDswebTest {

	@Test
	public void testTabSheetIsPresent() {
		TabSheet tabSheet = new TabSheet().withId("tabSheet");
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#tabSheet").length());
	}
	
	@Test
	public void testAlreadyAddedTabsRendered() {
		final String tabCaption = "Tab1";
		TabSheet tabSheet = new TabSheet().withDefaultTab(tabCaption, new Container().withId("tabContent")).withId("tabsheet");
		registerApp(tabSheet);
		assertEquals("Tab1", JQuery.select("#tabsheet > ul.nav-tabs > li > a").text());
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content > div.tab-pane > #tabContent").length());
	}
	
	@Test
	public void testTabTitleChange() {
		final String tabCaption = "Tab1";
		Tab tab = new Tab().withTitle(tabCaption).withContent(new Container().withId("tabContent"));
		TabSheet tabSheet = new TabSheet().withTab(tab).withId("tabsheet");
		registerApp(tabSheet);
		tab.setTitle("Tab2");
		assertEquals("Tab2", JQuery.select("#tabsheet > ul.nav-tabs > li > a").text());
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content > div.tab-pane > #tabContent").length());
	}

	@Test
	public void testTabAddedAfterRendering() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet");
		registerApp(tabSheet);
		Tab tab = new Tab().withTitle("Tab1").withContent(new Container().withId("tabContent"));
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content").length());
		assertEquals(0, JQuery.select("#tabsheet > div.tab-content").children().length());
		tabSheet.withTab(tab);
		assertEquals("Tab1", JQuery.select("#tabsheet > ul.nav-tabs > li > a").text());
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content > div.tab-pane > #tabContent").length());
	}
	
	@Test
	public void testTabInsertedAfterRendering() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet");
		Tab tab1 = new Tab().withTitle("Tab1").withContent(new Container().withId("tabContent1"));
		tabSheet.withTab(tab1);
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content > div.tab-pane:nth-child(1) > #tabContent1").length());
		Tab tab2 = new Tab().withTitle("Tab2").withContent(new Container().withId("tabContent2"));
		tabSheet.withTabOnSpecifiedIndex(tab2, 0);
		assertEquals("Tab2", JQuery.select("#tabsheet > ul.nav-tabs > li:nth-child(1) > a").text());
		assertEquals("Tab1", JQuery.select("#tabsheet > ul.nav-tabs > li:nth-child(2) > a").text());
		assertEquals(2, JQuery.select("#tabsheet > div.tab-content").children().length());
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content > div.tab-pane:nth-child(1) > #tabContent2").length());
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content > div.tab-pane:nth-child(2) > #tabContent1").length());
	}

	@Test
	public void testDefaultTabAddingOnSpecifiedIndex() {
		final String tabCaption1 = "Tab1";
		final String tabCaption2 = "Tab2";
		final String tabCaption3 = "Tab3";
		final String tabCaption4 = "Tab4";
		Container tabComponent = new Container();
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
			.withTab(new Tab().withTitle(tabCaption1).withContent(tabComponent))
			.withTab(new Tab().withTitle(tabCaption2).withContent(tabComponent))
			.withTab(new Tab().withTitle(tabCaption3).withContent(tabComponent))
			.withDefaultTabOnSpecifiedIndex(tabCaption4, tabComponent, 1);
		registerApp(tabSheet);
		assertEquals(tabCaption1, JQuery.select("#tabsheet ul li:nth-child(1)").text());
		assertEquals(tabCaption4, JQuery.select("#tabsheet ul li:nth-child(2)").text());
		assertEquals(tabCaption2, JQuery.select("#tabsheet ul li:nth-child(3)").text());
		assertEquals(tabCaption3, JQuery.select("#tabsheet ul li:nth-child(4)").text());
	}
	
	@Test
	public void testTabAddingOnSpecifiedIndex() {
		final String tabCaption1 = "Tab1";
		final String tabCaption2 = "Tab2";
		final String tabCaption3 = "Tab3";
		final String tabCaption4 = "Tab4";
		Container tabComponent = new Container();
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
			.withTab(new Tab().withTitle(tabCaption1).withContent(tabComponent))
			.withTab(new Tab().withTitle(tabCaption2).withContent(tabComponent))
			.withTab(new Tab().withTitle(tabCaption3).withContent(tabComponent))
			.withTabOnSpecifiedIndex(new Tab().withTitle(tabCaption4).withContent(tabComponent), 1);
		registerApp(tabSheet);
		assertEquals(tabCaption1, JQuery.select("#tabsheet ul li:nth-child(1)").text());
		assertEquals(tabCaption4, JQuery.select("#tabsheet ul li:nth-child(2)").text());
		assertEquals(tabCaption2, JQuery.select("#tabsheet ul li:nth-child(3)").text());
		assertEquals(tabCaption3, JQuery.select("#tabsheet ul li:nth-child(4)").text());
	}
	
	@Test
	public void testRemoveTab() {
		Tab tab = new Tab().withTitle("Tab1").withContent(new Container().withId("tabContent"));
		TabSheet tabSheet = new TabSheet().withTab(tab).withId("tabsheet");
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#tabsheet > div.tab-content > div.tab-pane > #tabContent").length());
		tabSheet.getModel().getTabList().remove(tab);
		assertEquals(0, JQuery.select("#tabContent").length());
	}
	
	@Test
	public void testAciveDefaultFirst() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
						.withDefaultTab("Tab1", new Label().withId("tabContent1"))
						.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		registerApp(tabSheet);
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent1").is(":visible"));
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent2").is(":visible"));
	}

	@Test
	public void testAciveUpdateActiveFromModel() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
						.withDefaultTab("Tab1", new Label().withId("tabContent1"))
						.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		registerApp(tabSheet);
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent1").is(":visible"));
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent2").is(":visible"));
		tabSheet.withActiveTabIndex(1);
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent1").is(":visible"));
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent2").is(":visible"));
	}

	@Test
	public void testAciveUpdateActiveByUser() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
						.withDefaultTab("Tab1", new Label().withId("tabContent1"))
						.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		registerApp(tabSheet);
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent1").is(":visible"));
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent2").is(":visible"));
		JQuery.select("#tabsheet ul li:nth-child(2) a").click();
		assertEquals(new Integer(1), tabSheet.getModel().getActiveTab());
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent1").is(":visible"));
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent2").is(":visible"));
	}
	
	@Test
	public void testActiveTabRemoved() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
						.withDefaultTab("Tab1", new Label().withId("tabContent1"))
						.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		registerApp(tabSheet);
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent1").is(":visible"));
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent2").is(":visible"));
		tabSheet.removeTab(tabSheet.getModel().getTabList().get(0));
		// there will be no active tab
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent2").is(":visible"));
	}

	@Test
	public void testTabBeforeActiveTabRemoved() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet").withActiveTabIndex(1)
						.withDefaultTab("Tab1", new Label().withId("tabContent1"))
						.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		registerApp(tabSheet);
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent1").is(":visible"));
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent2").is(":visible"));
		tabSheet.removeTab(tabSheet.getModel().getTabList().get(0));
		// the active tab is the first now
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent2").is(":visible"));
	}

	@Test
	public void testTabInsertedBeforeActiveTab() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet").withActiveTabIndex(0)
						.withDefaultTab("Tab1", new Label().withId("tabContent1"))
						.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		registerApp(tabSheet);
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent1").is(":visible"));
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent2").is(":visible"));
		tabSheet.withDefaultTabOnSpecifiedIndex("Tab3", new Label().withId("tabContent3"), 0);
		// the active tab is the second
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(1)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent3").is(":visible"));
		assertTrue(JQuery.select("#tabsheet ul li:nth-child(2)").hasClass("active"));
		assertTrue(JQuery.select("#tabContent1").is(":visible"));
		assertFalse(JQuery.select("#tabsheet ul li:nth-child(3)").hasClass("active"));
		assertFalse(JQuery.select("#tabContent2").is(":visible"));
	}

	@Test
	public void testTabShownEvent() {
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
				.withDefaultTab("Tab1", new Label().withId("tabContent1"))
				.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		EmptyEventHandlerMock eventHandlerMock = new EmptyEventHandlerMock();
		tabSheet.onTabShown(eventHandlerMock);
		registerApp(tabSheet);
		JQuery.select("#tabsheet ul li:nth-child(2) a").click();
		assertEquals(new Integer(1), tabSheet.getModel().getActiveTab());
		eventHandlerMock.assertInvoked();
	}
	
	/**
	 * Tests proper component disposal by asserting that click event handlers are retained
	 */
	@Test
	public void testButtonRemovedAndAdded() {
		EmptyEventHandlerMock eventHandlerMock = new EmptyEventHandlerMock();
		Tab tab1 = new Tab().withTitle("Tab1").withContent(new Button("abc").click(eventHandlerMock).withId("button"));
		TabSheet tabSheet = new TabSheet().withId("tabsheet")
				.withTab(tab1)
				.withDefaultTab("Tab2", new Label().withId("tabContent2"));
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#button:visible").length());
		tabSheet.removeTab(tab1);
		assertEquals(0, JQuery.select("#button:visible").length());
		tabSheet.withTabOnSpecifiedIndex(tab1, 0);
		tabSheet.withActiveTabIndex(0);
		assertEquals(1, JQuery.select("#button:visible").length());
		JQuery.select("#button").click();
		eventHandlerMock.assertInvoked();
	}
}
