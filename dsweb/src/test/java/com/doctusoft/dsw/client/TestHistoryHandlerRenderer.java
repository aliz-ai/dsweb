
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.HistoryHandler;
import com.google.gwt.user.client.History;

public class TestHistoryHandlerRenderer extends AbstractDswebTest {
	
	@Test
	public void testHistoryToken() {
		HistoryHandler historyHandler = new HistoryHandler();
		registerApp( historyHandler );
		historyHandler.getModel().setHistoryToken( "t1" );
		String token = History.getToken();
		assertEquals( "t1", token );
		
		History.newItem( "t2" );
		String token2 = historyHandler.getModel().getHistoryToken();
		assertEquals( "t2", token2 );
	}
}
