package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.gwt.ContainerRenderer;
import com.doctusoft.dsw.mvp.GwtPlaceControllerWrapper;
import com.doctusoft.dsw.mvp.IPlaceController;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.xedge.jquery.client.JQuery;

public class MvpExampleEntryPoint implements EntryPoint {
	
	private ClientFactory clientFactory;

	public void onModuleLoad() {
		final ContainerModel container = new ContainerModel();
		ContainerRenderer rootRenderer = new ContainerRenderer(container);
		JQuery.select("#content").append(rootRenderer.getWidget());

		clientFactory = GWT.create(ClientFactory.class);
		final IPlaceController placeController = clientFactory.getPlaceController();
		final ActivityMapper activityMapper = new PocActivityMapper(clientFactory);
		final ActivityManager activityManager = new ActivityManager(activityMapper, clientFactory.getEventBus());
		activityManager.setDisplay(new AcceptsOneWidget() {
			@Override
			public void setWidget(IsWidget w) {
				container.getChildren().clear();
				if (w != null) {
					// BaseComponent is hacked through standard GWT interfaces 
					container.getChildren().add(((HasComponentModel) w).getComponentModel());
				}
			}
		});
		
		final PlaceHistoryMapper historyMapper = GWT.create(PocPlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		
		historyHandler.register(((GwtPlaceControllerWrapper)placeController).getPlaceController(), clientFactory.getEventBus(), new PersonListPlace());
		
		historyHandler.handleCurrentHistory();
		
	}

}
