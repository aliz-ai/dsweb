package com.doctusoft.dsw.client.gwt;

import junit.framework.Assert;

import com.doctusoft.bean.binding.EmptyEventHandler;

/**
 * TODO evaluate easy-gwt-mock.
 * (It's an ANT project, we have to build and deploy it ... ) 
 */
public class EmptyEventHandlerMock implements EmptyEventHandler {
	
	private int invoked = 0;
	
	@Override
	public void handle() {
		invoked ++;
	}
	
	public void assertInvoked() {
		Assert.assertTrue("eventhandler not invoked", invoked > 0);
		invoked --;
	}

}
