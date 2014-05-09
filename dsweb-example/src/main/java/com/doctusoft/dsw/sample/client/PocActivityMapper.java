package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.sample.client.person.PersonDetailActivity;
import com.doctusoft.dsw.sample.client.person.PersonDetailPlace;
import com.doctusoft.dsw.sample.client.person.PersonListActivity;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PocActivityMapper implements ActivityMapper  {
	
	private ClientFactory clientFactory;

	public PocActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof PersonListPlace)
			return new PersonListActivity(clientFactory);
		if (place instanceof PersonDetailPlace)
			return new PersonDetailActivity(clientFactory, ((PersonDetailPlace) place).getPersonId());
		return null;
	}
}
