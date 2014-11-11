package com.doctusoft.dsw.client.gwt;

import org.junit.Ignore;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.InputTags;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestInputTagsRenderer extends AbstractDswebTest {
	
//	@Test
	@Ignore
	public void testDefaultTagsPresent() {
		InputTags inputTags = new InputTags().withId("inputTags");
		ObservableList<String> tagList = inputTags.getModel().getTagList();
		tagList.add("a");
//		tagList.add("b");
//		tagList.add("c");
		registerApp(inputTags);
		new Timer() {
			@Override
			public void run() {
				assertEquals(1, JQuery.select("#inputTags").length());
				assertEquals(3, JQuery.select(".bootstrap-tagsinput").length());
				finishTest();
			}
		}.schedule(200);
		delayTestFinish(500);
	}
	
//	@Test
	@Ignore
	public void testWithPlaceHolder() {
		InputTags inputTags = new InputTags()
			.withId("inputTags")
			.withPlaceHolder("ph1");
		registerApp(inputTags);
		new Timer() {
			@Override
			public void run() {
				assertEquals("ph1", JQuery.select("#inputTags").attr("placeholder"));
				finishTest();
			}
		}.schedule(200);
		delayTestFinish(500);
	}
	
}
