package com.doctusoft.dsw.sample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AbstractCallback<T> implements AsyncCallback<T> {
	
	private ClientFactory clientFactory;

	public AbstractCallback(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		// TODO handle failures
		System.out.println(caught);
	}

}
