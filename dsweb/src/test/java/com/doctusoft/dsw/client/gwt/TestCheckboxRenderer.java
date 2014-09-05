
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Checkbox;
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.xedge.jquery.client.JQuery;

public class TestCheckboxRenderer extends AbstractDswebTest {
	
	@Test
	public void testLabel() {
		Checkbox checkbox = new Checkbox( "initial" ).withId( "checkbox" );
		registerApp( checkbox );
		assertEquals( "initial", JQuery.select( "#checkbox" ).text() );
		checkbox.getModel().setLabel( "changed" );
		assertEquals( "changed", JQuery.select( "#checkbox" ).text() );
	}
	
	@Test
	public void testChecked() {
		Checkbox checkbox = new Checkbox().withId( "checkbox" );
		CheckboxModel model = checkbox.getModel();
		model.setChecked( true );
		registerApp( checkbox );
		assertTrue( JQuery.select( "#checkbox > input" ).is( ":checked" ) );
		model.setChecked( false );
		assertFalse( JQuery.select( "#checkbox > input" ).is( ":checked" ) );
	}
	
	@Test
	public void testCheckboxClick() {
		Checkbox checkbox = new Checkbox().withId( "checkbox" );
		CheckboxModel model = checkbox.getModel();
		model.setChecked( true );
		registerApp( checkbox );
		JQuery.select( "#checkbox > input" ).click();
		assertFalse( model.getChecked() );
		JQuery.select( "#checkbox > input" ).click();
		assertTrue( model.getChecked() );
	}
}
