package com.doctusoft.dsw.sample.server.devmode;

import com.doctusoft.dsw.mvp.AbstractIntegartionClientFactory;
import com.doctusoft.dsw.mvp.IntegrationServiceFactory;
import com.doctusoft.dsw.mvp.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.PocActivityMapper;
import com.doctusoft.dsw.sample.client.person.PersonDetailActivity;
import com.doctusoft.dsw.sample.client.person.PersonDetailView;
import com.doctusoft.dsw.sample.client.person.PersonListActivity;
import com.doctusoft.dsw.sample.client.person.PersonListView;
import com.doctusoft.dsw.sample.client.person.PersonRemoteServiceAsync;
import com.doctusoft.dsw.sample.server.person.PersonRPC;
import com.google.gwt.activity.shared.ActivityMapper;

public class JvmClientFactoryImpl extends AbstractIntegartionClientFactory implements ClientFactory {
	
	@Override
	protected ActivityMapper createActivityMapper() {
		return new PocActivityMapper(this);
	}
	
	private ViewOf<PersonListActivity> personListView = null;

	public ViewOf<PersonListActivity> getPersonListView() {
		if (personListView == null) {
			personListView = new PersonListView();
		}
		return personListView;
	}

	private ViewOf<PersonDetailActivity> personDetailView = null;

	public ViewOf<PersonDetailActivity> getPersonDetailView() {
		if (personDetailView == null) {
			personDetailView = new PersonDetailView();
		}
		return personDetailView;
	}

	private PersonRemoteServiceAsync personRemoteServiceAsync = null;
	
	public PersonRemoteServiceAsync getPersonRemoteServiceAsync() {
		if (personRemoteServiceAsync == null) {
			// TODO Well this is not right
			personRemoteServiceAsync = IntegrationServiceFactory
					.getService(PersonRemoteServiceAsync.class, new PersonRPC());
		}
		return personRemoteServiceAsync;
	}
}
