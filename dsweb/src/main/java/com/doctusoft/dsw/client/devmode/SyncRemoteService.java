package com.doctusoft.dsw.client.devmode;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("sync")
public interface SyncRemoteService extends RemoteService {

	public List<String> sync(List<String> clientMessages);
	
	public String fullSync();
	
	public int registerClient();
	
}
