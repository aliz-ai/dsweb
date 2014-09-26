package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.KeyEventHandlerMock;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.google.common.base.Objects;
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
	
	@Test
	public void testStyle() {
		Label label = new Label().withId("label").withStyle("width: 150px");
		registerApp(label);
		assertEquals("150px", JQuery.select("#label").css("width"));
		label.withStyle("width: 125px");
		assertEquals("125px", JQuery.select("#label").css("width"));
		label.withStyle(null);
		assertNull(JQuery.select("#label").attr("style"));
	}
	
	@Test
	public void testTabIndex() {
		Label label = new Label().withId("label");
		registerApp(label);
		assertNull(JQuery.select("#label").attr("tabindex"));
		label.withTabIndex( 0 );
		assertEquals("0", JQuery.select("#label").attr("tabindex"));
		label.withTabIndex(1);
		assertEquals("1", JQuery.select("#label").attr("tabindex"));
	}
	
	@Test
	public void testClick() {
		EmptyEventHandlerMock clickHandler = new EmptyEventHandlerMock();
		Label label = new Label().withId("label").click(clickHandler);
		registerApp(label);
		JQuery.select("#label").click();
		clickHandler.assertInvoked();
	}
	
	@Test
	public void testClickAttachedLater() {
		EmptyEventHandlerMock clickHandler = new EmptyEventHandlerMock();
		Label label = new Label().withId("label");
		registerApp(label);
		JQuery.select("#label").click();
		label.click(clickHandler);
		JQuery.select("#label").click();
		clickHandler.assertInvoked();
	}
	
	@Test
	public void testClickNoListenerAttached() {
		Label label = new Label().withId("label");
		registerApp(label);
		label.getModel().setClicked(new ComponentEvent());
		// without the haslisteners flag, the click event is not propagated to the model
		ValueChangeListenerMock<Boolean> clickMock = new ValueChangeListenerMock<Boolean>();
		ComponentEvent_._fired.addChangeListener(label.getModel().getClicked(), clickMock);
		JQuery.select("#label").click();
		clickMock.assertNoValueChanged();
		//  but when it's set, events get propagated
		label.getModel().getClicked().setHasListeners(true);
		JQuery.select("#label").click();
		clickMock.assertValueChanged(true);
		clickMock.assertValueChanged(false);
	}
	
	@Test
	public void testFocusAfterRendered() {
		InputText inputText = new InputText().withId("text");
		registerApp(inputText);
		assertEquals(0, JQuery.select("#text:focus").length());
		inputText.focus();
		assertEquals(1, JQuery.select("#text:focus").length());
	}

	@Test
	public void testKeyPressedNonRestricted() {
		KeyEventHandlerMock mock = new KeyEventHandlerMock();
		InputText inputText = new InputText().withId("text").keypress(mock);
		registerApp(inputText);
		fireKeyPress(21, JQuery.select("#text"));
		assertEquals(21, mock.getReceivedKeyCode());
	}
	
	@Test
	public void testKeyPressedRestrictedSingle() {
		final KeyEventHandlerMock mock = new KeyEventHandlerMock();
		final InputText inputText = new InputText().withId("text");
		// register directly on the model so that we capture all events
		Bindings.obs(inputText.getModel()).get(BaseComponentModel_._keyPressed).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					mock.handle(inputText.getModel().getKeyPressed().getKeyEvent());
				}
			}
		});
		inputText.keypress(new KeyEventHandlerMock(), 21);
		registerApp(inputText);
		fireKeyPress(22, JQuery.select("#text"));
		assertEquals(0, mock.getReceivedKeyCode());
		fireKeyPress(21, JQuery.select("#text"));
		assertEquals(21, mock.getReceivedKeyCode());
	}

	protected static native void fireKeyPress(int keyCode, JQuery target) /*-{
		var e = $wnd.$.Event("keypress");
		e.which = keyCode;
		target.trigger(e);
	}-*/;
}
