package com.doctusoft.dsw.sample.client;

public abstract class BaseActivity<Presenter, Place extends com.google.gwt.place.shared.Place> extends com.doctusoft.dsw.client.BaseActivity<Presenter, Place, ClientFactory> {
	
	public BaseActivity(ClientFactory clientFactory, Place place) {
		super(clientFactory, place);
	}

}
