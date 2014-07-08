package com.doctusoft.dsw.client.exc;

import com.doctusoft.dsw.client.comp.Alert;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.google.web.bindery.event.shared.EventBus;

public class BasicExceptionDisplayer {
	
	public BasicExceptionDisplayer(EventBus eventBus, final BaseContainer container) {
		eventBus.addHandler(ApplicaitonExceptionEvent.TYPE, new ApplicaitonExceptionEvent.Handler() {
			@Override
			public void onApplicationException(ApplicaitonExceptionEvent event) {
				container.add(new Alert("" + event.getThrowable()));
			}
		});
	}

}
