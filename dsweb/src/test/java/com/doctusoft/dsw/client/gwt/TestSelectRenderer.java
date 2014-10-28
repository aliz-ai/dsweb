
package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.xedge.jquery.client.JQuery;

public class TestSelectRenderer extends AbstractDswebTest {
	
	@Test
	public void testAreOptionsRendered() {
		Select<MockSelectModel> select = createSelectWithTwoOptions();
		assertEquals( "first", JQuery.select( "select > option:first-child" ).text() );
		assertEquals( "second", JQuery.select( "select > option:nth-child(2)" ).text() );
		SelectItemModel third = new SelectItemModel();
		third.setCaption( "third" );
		select.getModel().getSelectItemsModel().add( third );
		assertEquals( "third", JQuery.select( "select > option:nth-child(3)" ).text() );
	}
	
	@Test
	public void testSelectedIndex() {
		Select<MockSelectModel> select = createSelectWithTwoOptions();
		assertEquals( 0, select.getModel().getSelectedIndex() );
		JQuery.select( "#select" ).val( "second" ).change();
		assertEquals( 1, select.getModel().getSelectedIndex() );
		JQuery.select( "#select" ).val( "first" ).change();
		assertEquals( 0, select.getModel().getSelectedIndex() );
	}
	
	private Select<MockSelectModel> createSelectWithTwoOptions() {
		ArrayList<SelectItem<MockSelectModel>> selectItems = new ArrayList<SelectItem<MockSelectModel>>();
		selectItems.add( createSelectItemWithName( "first" ) );
		selectItems.add( createSelectItemWithName( "second" ) );
		Select<MockSelectModel> select = new Select<MockSelectModel>().withSelectItems( selectItems ).withId( "select" );
		registerApp( select );
		return select;
	}
	
	private SelectItem<MockSelectModel> createSelectItemWithName( String name ) {
		SelectItem<MockSelectModel> first = new SelectItem<TestSelectRenderer.MockSelectModel>();
		MockSelectModel firstModel = new MockSelectModel( name );
		first.setValue( firstModel );
		first.setCaption( firstModel.getName() );
		return first;
	}

	/**
	 * Only test if disabling works. Other aspects of {@link DisabledAttributeRenderer} are tested in {@link TestInputTextRenderer}
	 */
	@Test
	public void testDisabledFirst() {
		final InputText inputText = new InputText().withId("input").withDisabled(true);
		registerApp(inputText);
		assertTrue(JQuery.select("#input").is(":disabled"));
	}


	
	@Getter
	@AllArgsConstructor
	private static class MockSelectModel {
		
		private final String name;
	}
}
