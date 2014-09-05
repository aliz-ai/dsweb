
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Cell;
import com.doctusoft.dsw.client.comp.Label;
import com.xedge.jquery.client.JQuery;

public class TestCellRenderer extends AbstractDswebTest {
	
	@Test
	public void testSpan() {
		Cell cell = new Cell().withId( "cell" );
		registerApp( cell );
		JQuery jqCell = JQuery.select( "#cell" );
		assertTrue( jqCell.hasClass( "span1" ) );
		cell.withSpan( 6 );
		assertTrue( jqCell.hasClass( "span6" ) );
		assertFalse( jqCell.hasClass( "span1" ) );
	}
	
	@Test
	public void testOffset() {
		Cell cell = new Cell().withId( "cell" ).withOffset( 3 );
		registerApp( cell );
		JQuery jqCell = JQuery.select( "#cell" );
		assertTrue( jqCell.hasClass( "offset3" ) );
		cell.withOffset( 6 );
		assertTrue( jqCell.hasClass( "offset6" ) );
		assertFalse( jqCell.hasClass( "offset3" ) );
	}
	
	@Test
	public void testAddAndRemoveWidgets() {
		Cell cell = new Cell().withId( "cell" );
		registerApp( cell );
		JQuery jqCell = JQuery.select( "#cell" );
		assertEquals( "", jqCell.text() );
		cell.add( new Label( "added" ) );
		assertEquals( "added", jqCell.text() );
		cell.getModel().getChildren().clear();
		assertEquals( "", jqCell.text() );
	}
}
