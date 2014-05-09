package com.doctusoft.dsw.client.devmode;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SyncRemoteServiceAsync {

	void sync(List<String> clientMessages, AsyncCallback<List<String>> callback);

	void fullSync(AsyncCallback<String> callback);

	void registerClient(AsyncCallback<Integer> callback);
	
}
