package com.doctusoft.dsw.sample.server.devmode;

import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.server.devmode.AbstractDevModeSyncRPC;

public class SyncRPC extends AbstractDevModeSyncRPC {
	
	@Override
	public void initApplication(ContainerModel container) {
		new JvmEntryPoint(container);
	}
	
}
