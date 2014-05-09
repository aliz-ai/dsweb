package com.doctusoft.dsw.sample.server.devmode;

import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.server.devmode.AbstractDevModeSyncRPC;

public class SyncRPC extends AbstractDevModeSyncRPC {
	
	@Override
	public void initApplication(Container container) {
		new JvmEntryPoint(container);
	}
	
}
