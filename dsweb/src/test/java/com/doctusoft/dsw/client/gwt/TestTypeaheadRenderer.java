
package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.xedge.jquery.client.JQuery;

@Log
public class TestTypeaheadRenderer extends AbstractDswebTest {
	
	@Test
	public void testSelectItems() {
		Typeahead<String> typeahead = new Typeahead<String>().withId( "typeahead" ).withSelectItems( createDummySelectItems() );
		TypeaheadModel model = typeahead.getModel();
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
}
