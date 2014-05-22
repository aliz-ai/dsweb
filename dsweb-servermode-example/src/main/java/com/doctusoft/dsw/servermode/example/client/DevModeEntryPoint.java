package com.doctusoft.dsw.servermode.example.client;

import com.doctusoft.dsw.servermode.client.util.DevModeSynchronizer;
import com.doctusoft.dsw.servermode.example.client.custom.CustomModelObjectFactory;
import com.google.gwt.core.client.EntryPoint;
import com.xedge.jquery.client.JQuery;

public class DevModeEntryPoint implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		new DevModeSynchronizer(new CustomModelObjectFactory()) {
			
			@Override
			public void appendContainer(JQuery renderedContainer) {
				JQuery.select("#content").append(renderedContainer);
			}
		};
	}

}
