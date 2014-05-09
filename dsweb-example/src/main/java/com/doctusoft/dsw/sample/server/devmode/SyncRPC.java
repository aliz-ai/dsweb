package com.doctusoft.dsw.sample.server.devmode;

import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.sample.client.custom.CustomModelObjectFactory;
import com.doctusoft.dsw.server.devmode.AbstractDevModeSyncRPC;
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
