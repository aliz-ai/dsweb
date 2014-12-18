package com.doctusoft.dsw.client.comp;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.util.JUnitDeferrerImpl;

public class TestTypeahead {
	
	@ObservableProperty
	private String selectValue;

	@ObservableProperty
	private String customText;
	
	@BeforeClass
	public static void setupClass() {
		new JUnitDeferrerImpl();
	}

	@Test
	public void testInitWithCustomText() {
		Typeahead<String> typeahead = new Typeahead<String>();
		typeahead.getModel().setSelectedItem(null);
		typeahead.getModel().setCustomText("initial value");
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals("initial value", typeahead.getModel().getCustomText());
	}

}
