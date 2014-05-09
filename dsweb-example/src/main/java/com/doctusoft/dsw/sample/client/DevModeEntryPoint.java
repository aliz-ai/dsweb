package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.DevModeSynchronizer;
import com.google.gwt.core.client.EntryPoint;
import com.xedge.jquery.client.JQuery;

public class DevModeEntryPoint implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		new DevModeSynchronizer() {
			
			@Override
			public void appendContainer(JQuery renderedContainer) {
				JQuery.select("#content").append(renderedContainer);
			}
		};
	}

}
