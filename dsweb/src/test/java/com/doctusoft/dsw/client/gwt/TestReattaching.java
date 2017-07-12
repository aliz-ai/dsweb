package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestReattaching extends AbstractDswebTest {

	@Test
	public void testRendererNotDisposedWithinTimeframe() {
		final Container container = new Container();
		final InputText inputText = new InputText().withId("input");
		final Label label = new Label();
		container.add(inputText);
		container.add(label);
		final InputTextModel model = inputText.getModel();
		model.setValue("first");
		registerApp(container);
		JQuery.select("#input").attr("marked", "marked");
		container.remove(inputText);
		delayTestFinish(1000);
		new Timer() {
			@Override
			public void run() {
				container.remove(label); // to trigger final disposing of other components
				container.add(inputText);
				// we still have the same element
				assertEquals("marked", JQuery.select("#input").attr("marked"));
				// and event handlers are still attached
				JQuery.select("#input").val("newval").change();
				assertEquals("newval", model.getValue());
				finishTest();
			}
		}.schedule(250);
	}

	@Test
	public void testRendererDisposed() {
		final Container container = new Container();
		final InputText inputText = new InputText().withId("input");
		final Label label = new Label();
		container.add(inputText);
		container.add(label);
		final InputTextModel model = inputText.getModel();
		model.setValue("first");
		registerApp(container);
		JQuery.select("#input").attr("marked", "marked");
		container.remove(inputText);
		delayTestFinish(2000);
		new Timer() {
			@Override
			public void run() {
				container.remove(label); // to trigger final disposing of other components
				container.add(inputText);
				// the mark we've put on the element has gone, thus we really have a new renderer
				assertEquals(null, JQuery.select("#input").attr("marked"));
				// but event handlers still work fine
				JQuery.select("#input").val("newval").change();
				assertEquals("newval", model.getValue());
				finishTest();
			}
		}.schedule(1100);
	}
}
