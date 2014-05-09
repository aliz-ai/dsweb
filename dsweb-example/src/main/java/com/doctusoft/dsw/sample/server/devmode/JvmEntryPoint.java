package com.doctusoft.dsw.sample.server.devmode;

import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HistoryHandler;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.sample.client.PocPlaceHistoryMapper;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.doctusoft.dsw.server.devmode.HistoryManager;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public class JvmEntryPoint {
	
	private JvmClientFactoryImpl clientFactory;

	public JvmEntryPoint(final ContainerModel container) {
		clientFactory = new JvmClientFactoryImpl();
		clientFactory.getActivityManager().setDisplay(new AcceptsOneWidget() {
			@Override
			public void setWidget(IsWidget w) {
				container.getChildren().clear();
				if (w != null) {
					container.getChildren().add(((HasComponentModel) w).getComponentModel());
				}
			}
		});
		clientFactory.getPlaceController().goTo(new PersonListPlace());
		HistoryHandler historyHandler = new HistoryHandler();
		container.getChildren().add(historyHandler.getComponentModel());
		new HistoryManager(historyHandler, clientFactory.getPlaceController(), PocPlaceHistoryMapper.class, new PersonListPlace());
	}

}
