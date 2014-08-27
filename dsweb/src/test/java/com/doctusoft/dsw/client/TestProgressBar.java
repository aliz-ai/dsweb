package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.ProgressBar;
import com.xedge.jquery.client.JQuery;

public class TestProgressBar extends AbstractDswebTest {
	
	@Test
	public void testProgressBarIsPresent() {
		ProgressBar progressBar = new ProgressBar();
		progressBar.withId("progressBar");
		registerApp(progressBar);
		assertEquals(1, JQuery.select("#progressBar").length());
	}
	
	@Test
	public void testProgressBarValueChange() {
		int initProgress = 25;
		int changeProgress = 79;
		ProgressBar progressBar = new ProgressBar();
		progressBar.setProgress(initProgress);
		progressBar.withId("progressBar");
		registerApp(progressBar);
		assertEquals("width: " + initProgress+"%", JQuery.select(".bar").attr("style"));
		progressBar.setProgress(changeProgress);
		assertEquals("width: " + changeProgress+"%", JQuery.select(".bar").attr("style"));
	}

}
