
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.Button;
import com.xedge.jquery.client.JQuery;

public class TestButtonRenderer extends AbstractDswebTest {
	
	@ObservableProperty
	private String caption;
	
	@Test
	public void testButtonIsPresent() {
		registerApp( new Button().withId( "button" ) );
		assertEquals( 1, JQuery.select( "#button" ).length() );
	}
	
	@Test
	public void testCaption() {
		final String initialCaption = "proba";
		setCaption( initialCaption );
		registerApp( new Button().withId( "button" ).bindCaption( Bindings.obs( this ).get( TestButtonRenderer_._caption ) ) );
		assertEquals( initialCaption, JQuery.select( "#button" ).text() );
		
		String changedCaption = "uj";
		setCaption( changedCaption );
		assertEquals( changedCaption, JQuery.select( "#button" ).text() );
		
	}
}
