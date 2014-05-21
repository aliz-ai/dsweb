package com.doctusoft.dsw.sample.client;

import lombok.Getter;

import com.doctusoft.dsw.mvp.GwtPlaceControllerWrapper;
import com.doctusoft.dsw.mvp.IPlaceController;
import com.doctusoft.dsw.mvp.ViewOf;
import com.doctusoft.dsw.sample.client.person.PersonDetailActivity;
import com.doctusoft.dsw.sample.client.person.PersonDetailView;
import com.doctusoft.dsw.sample.client.person.PersonListActivity;
import com.doctusoft.dsw.sample.client.person.PersonListView;
import com.doctusoft.dsw.sample.client.person.PersonRemoteService;
import com.doctusoft.dsw.sample.client.person.PersonRemoteServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactoryImpl implements ClientFactory {
	
	@Getter
	private final EventBus eventBus = new SimpleEventBus();

	@Getter
	private final IPlaceController placeController = new GwtPlaceControllerWrapper(new PlaceController(eventBus));
	
	@Getter(lazy=true)
	private final ViewOf<PersonListActivity> personListView = new PersonListView();
	
	@Getter(lazy=true)
	private final ViewOf<PersonDetailActivity> personDetailView = new PersonDetailView();

	@Getter(lazy=true)
	private final PersonRemoteServiceAsync personRemoteServiceAsync = GWT.create(PersonRemoteService.class);
	
}
