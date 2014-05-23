package com.doctusoft.dsw.servermode.example.server.devmode;

import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.sample.server.devmode.JvmEntryPoint;
import com.doctusoft.dsw.servermode.example.client.custom.CustomModelObjectFactory;
import com.doctusoft.dsw.servermode.server.AbstractDevModeSyncRPC;
import com.doctusoft.synchronic.serialization.ObjectModelFactory;

public class SyncRPC extends AbstractDevModeSyncRPC {
	
	@Override
	public void initApplication(ContainerModel container) {
		new JvmEntryPoint(container);
	}
	
	@Override
	public ObjectModelFactory getObjectModelFactory() {
		return new CustomModelObjectFactory();
	}
}
