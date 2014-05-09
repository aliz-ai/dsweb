package com.doctusoft.dsw.sample.server.devmode;

import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HistoryHandler;
import com.doctusoft.dsw.client.comp.IsComponent;
import com.doctusoft.dsw.sample.client.PocPlaceHistoryMapper;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public class JvmEntryPoint {
	
	private JvmClientFactoryImpl clientFactory;

	public JvmEntryPoint(final Container container) {
		clientFactory = new JvmClientFactoryImpl();
		clientFactory.getActivityManager().setDisplay(new AcceptsOneWidget() {
			@Override
			public void setWidget(IsWidget w) {
				container.getChildren().clear();
				if (w != null) {
					container.add(((IsComponent) w).asComponent());
				}
			}
		});
		clientFactory.getPlaceController().goTo(new PersonListPlace());
		HistoryHandler historyHandler = new HistoryHandler();
		container.add(historyHandler);
		new HistoryManager(historyHandler, clientFactory.getPlaceController(), PocPlaceHistoryMapper.class, new PersonListPlace());
	}

}
