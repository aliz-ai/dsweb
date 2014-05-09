package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.sample.client.person.PersonDetailActivity;
import com.doctusoft.dsw.sample.client.person.PersonListActivity;
import com.doctusoft.dsw.sample.client.person.PersonRemoteServiceAsync;
import com.doctusoft.gwt.light.mvp.IPlaceController;
import com.doctusoft.gwt.light.mvp.ViewOf;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	
	public EventBus getEventBus();
	
	public IPlaceController getPlaceController();
	
	public ViewOf<PersonListActivity> getPersonListView();
	
	public ViewOf<PersonDetailActivity> getPersonDetailView();

	public PersonRemoteServiceAsync getPersonRemoteServiceAsync();
}
