package com.doctusoft.dsw.sample.client;

/*
 * #%L
 * dsweb-example
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
		clientFactory = GWT.create(ClientFactory.class);

		final ExampleApplication app = new ExampleApplication(clientFactory);
		applicationFrame = app.createFrameWidgets();
		JQuery.select("#content").append(rendererFactory.getRenderer(applicationFrame.getComponentModel()).getWidget());

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
