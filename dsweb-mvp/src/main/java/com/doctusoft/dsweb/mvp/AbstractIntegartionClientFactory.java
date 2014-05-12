package com.doctusoft.dsweb.mvp;

import lombok.Getter;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public abstract class AbstractIntegartionClientFactory {

	@Getter
	private EventBus eventBus = new SimpleEventBus();
	
	@Getter
	private IPlaceController placeController;
	
	@Getter
	private Activity currentPresenter;

	@Getter
	private IntegrationActivityManager activityManager;
	
	protected abstract ActivityMapper createActivityMapper();
	
	public AbstractIntegartionClientFactory() {
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
