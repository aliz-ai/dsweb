package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.InputTags;
import com.xedge.jquery.client.JQuery;

public class TestInputTagsRenderer extends AbstractDswebTest {
	
	@Test
	public void testTagsInputIsPresent() {
		InputTags inputTags = new InputTags().withId("inputTags");
		inputTags.getModel().setTagSuggestions(new ObservableList<String>());
		registerApp(inputTags);
		assertEquals(1, JQuery.select("#inputTags").length());
	}
	
}
