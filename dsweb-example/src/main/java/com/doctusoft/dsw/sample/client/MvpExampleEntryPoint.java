package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.mvp.client.GwtPlaceControllerWrapper;
import com.doctusoft.dsw.mvp.client.IPlaceController;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.xedge.jquery.client.JQuery;

public class MvpExampleEntryPoint implements EntryPoint {
	
	private HasComponentModel applicationFrame;
	
	protected static RendererFactory<JQuery> rendererFactory = GWT.create(RendererFactory.class);
	
	protected ClientFactory clientFactory;
	
	public void onModuleLoad() {
		final ExampleApplication app = new ExampleApplication();
		applicationFrame = app.createFrameWidgets();
		JQuery.select("#content").append(rendererFactory.getRenderer(applicationFrame.getComponentModel()).getWidget());

		clientFactory = GWT.create(ClientFactory.class);
		final IPlaceController placeController = clientFactory.getPlaceController();
		final ActivityMapper activityMapper = new PocActivityMapper(clientFactory);
		final ActivityManager activityManager = new ActivityManager(activityMapper, clientFactory.getEventBus());
		activityManager.setDisplay(new AcceptsOneWidget() {
			@Override
			public void setWidget(IsWidget w) {
				// BaseComponent is hacked through standard GWT interfaces 
				app.showView((HasComponentModel) w);
			}
		});

		final PlaceHistoryMapper historyMapper = GWT.create(PocPlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		
		historyHandler.register(((GwtPlaceControllerWrapper)placeController).getPlaceController(), clientFactory.getEventBus(), app.getDefaultPlace());
		
		historyHandler.handleCurrentHistory();
		
	}

}
