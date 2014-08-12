package com.doctusoft.dsw.mvp;

/*
 * #%L
 * dsweb-mvp
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


import lombok.Getter;

import com.doctusoft.dsw.mvp.client.IPlaceController;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public abstract class AbstractIntegrationClientFactory {

	@Getter
	private EventBus eventBus = new SimpleEventBus();
	
	@Getter
	private IPlaceController placeController;
	
	@Getter
	private Activity currentPresenter;

	@Getter
	private IntegrationActivityManager activityManager;
	
	protected abstract ActivityMapper createActivityMapper();
	
	public AbstractIntegrationClientFactory() {
		final ActivityMapper activityMapper = createActivityMapper();
		activityManager = new IntegrationActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(new AcceptsOneWidget() {
			@Override
			public void setWidget(IsWidget w) {
			}
		});
		placeController = new IPlaceController() {
			@Override
			public void goTo(Place newPlace) {
				activityManager.onPlaceChange(new PlaceChangeEvent(newPlace));
				currentPresenter = activityManager.getCurrentActivity();
			}
		};
	}
}
