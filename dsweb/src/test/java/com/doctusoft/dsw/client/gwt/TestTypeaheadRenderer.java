
package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.client.util.GWTTimerDeferrerImpl;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

@Log
public class TestTypeaheadRenderer extends AbstractDswebTest {
	
	@Test
	public void testSelectItems() {
		new GWTTimerDeferrerImpl();
		Typeahead<String> typeahead = new Typeahead<String>().withId( "typeahead" ).withSelectItems( createDummySelectItems() );
		registerApp( typeahead );
		JQuery jqRoot = JQuery.select( ":root" );
		log.info( jqRoot.html() );
	}
	
	private List<SelectItem<String>> createDummySelectItems() {
		List<SelectItem<String>> items = new ArrayList<SelectItem<String>>();
		items.add( createDummySelectItem( "1" ) );
		items.add( createDummySelectItem( "2" ) );
		items.add( createDummySelectItem( "3" ) );
		return items;
	}
	
	private SelectItem<String> createDummySelectItem( String name ) {
		SelectItem<String> item = new SelectItem<String>();
		item.setId( name );
		item.setCaption( name );
		item.setValue( name );
		return item;
	}
	
	@Test
	public void testCustomTextTyping() {
		new GWTTimerDeferrerImpl();
		final Typeahead<String> typeahead = new Typeahead<String>().allowCustomText().withId("typeahead_costumtext").withSelectItems(createDummySelectItems());
		registerApp(typeahead);
		new Timer() {
			@Override
			public void run() {
				JQuery jqTypeahead = JQuery.select("#typeahead_costumtext");
				jqTypeahead.val("Proba");
				jqTypeahead.change();
				assertEquals("Proba", typeahead.getModel().getCustomText());
				assertEquals(null, typeahead.getModel().getSelectedItem());
				jqTypeahead.val("1");
				jqTypeahead.change();
				assertEquals(null, typeahead.getModel().getCustomText());
				assertEquals(typeahead.getModel().getSelectItemsModel().get(0), typeahead.getModel().getSelectedItem());
				finishTest();
			}
		}.schedule(25);
		delayTestFinish(100);
	}
}
