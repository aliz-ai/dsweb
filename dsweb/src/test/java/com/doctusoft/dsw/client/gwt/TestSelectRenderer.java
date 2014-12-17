
package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.util.GWTTimerDeferrerImpl;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestSelectRenderer extends AbstractDswebTest {
	
	@Test
	public void testAreOptionsRendered() {
		new GWTTimerDeferrerImpl();	// @Before doesn't seem to work
		Select<MockSelectModel> select = createSelectWithTwoOptions();
		new Timer() {
			@Override
			public void run() {
				assertEquals( "first", JQuery.select( "select > option:first-child" ).text() );
				assertEquals( "second", JQuery.select( "select > option:nth-child(2)" ).text() );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}
	
	@Test
	public void testOptionInsertedLater() {
		new GWTTimerDeferrerImpl();	// @Before doesn't seem to work
		final Select<MockSelectModel> select = createSelectWithTwoOptions();
		new Timer() {
			@Override
			public void run() {
				// add an item to the model (we have to do it in a timer though)
				SelectItemModel third = new SelectItemModel();
				third.setCaption( "third" );
				select.getModel().getSelectItemsModel().add( third );
			}
		}.schedule(25);
		new Timer() {
			@Override
			public void run() {
				assertEquals( "third", JQuery.select( "select > option:nth-child(3)" ).text() );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}
	
	@Test
	public void testSelectedIndexDefaultValue() {
		new GWTTimerDeferrerImpl();	// @Before doesn't seem to work
		final Select<MockSelectModel> select = createSelectWithTwoOptions();
		new Timer() {
			@Override
			public void run() {
				assertEquals( 0, select.getModel().getSelectedIndex() );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}
	
	@Test
	public void testSelectByClicking() {
		new GWTTimerDeferrerImpl();	// @Before doesn't seem to work
		final Select<MockSelectModel> select = createSelectWithTwoOptions();
		new Timer() {
			@Override
			public void run() {
				JQuery.select( "#select" ).val( "second" ).change();
			}
		}.schedule(25);
		new Timer() {
			@Override
			public void run() {
				assertEquals( 1, select.getModel().getSelectedIndex() );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
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
	 * Only test if disabling works. Other aspects of {@link EnabledAttributeRenderer} are tested in {@link TestInputTextRenderer}
	 */
	@Test
	public void testDisabledFirst() {
		final InputText inputText = new InputText().withId("input").withEnabled(false);
		registerApp(inputText);
		assertTrue(JQuery.select("#input").is(":disabled"));
	}
	
	@Getter
	@AllArgsConstructor
	private static class MockSelectModel {
		
		private final String name;
	}
}
