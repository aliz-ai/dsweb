
package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestSelectRenderer extends AbstractDswebTest {
	
	@ObservableProperty
	private String selectedItem;

	@Test
	public void testAreOptionsRendered() {
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
		final Select<MockSelectModel> select = createSelectWithTwoOptions();
		new Timer() {
			@Override
			public void run() {
				assertEquals( select.getModel().getSelectItemsModel().get(0), select.getModel().getSelectedItem() );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}

	@Test
	public void testSelectByClicking() {
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
				assertEquals( select.getModel().getSelectItemsModel().get(1), select.getModel().getSelectedItem() );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}

	@Test
	public void testValueRetainOnItemsReplaced() {
		final Select<MockSelectModel> select = createSelectWithTwoOptions();
		new Timer() {
			@Override
			public void run() {
				select.getModel().setSelectedItem(select.getModel().getSelectItemsModel().get(1));
				select.getModel().setSelectItemsModel(select.getModel().getSelectItemsModel());
			}
		}.schedule(25);
		new Timer() {
			@Override
			public void run() {
				assertEquals("second", JQuery.select( "#select" ).val());
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}

	@Test
	public void testNullOptionCaptionRendered() {
		final Select<MockSelectModel> select = createSelectWithTwoOptions().withNullOptionCaption("Please select");
		new Timer() {
			@Override
			public void run() {
				assertEquals("Please select", JQuery.select( "#select" ).val());
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}

	@Test
	public void testSelectNullOption() {
		final Select<MockSelectModel> select = createSelectWithTwoOptions().withNullOptionCaption("Please select");
		new Timer() {
			@Override
			public void run() {
				select.getModel().setSelectedItem(select.getModel().getSelectItemsModel().get(0));
			}
		}.schedule(25);
		new Timer() {
			@Override
			public void run() {
				assertEquals("first", JQuery.select( "#select" ).val());
				JQuery.select( "#select" ).val( (String) null ).change();
				assertEquals(null, select.getModel().getSelectedItem());
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}

	@Test
	public void testNullOptionRemoved() {
		final Select<MockSelectModel> select = createSelectWithTwoOptions().withNullOptionCaption("Please select");
		new Timer() {
			@Override
			public void run() {
				assertEquals("Please select", JQuery.select( "#select" ).val());
				select.withNullOptionCaption(null);
			}
		}.schedule(25);
		new Timer() {
			@Override
			public void run() {
				assertEquals("first", JQuery.select( "#select" ).val());
				assertEquals(2, JQuery.select( "#select option" ).length());
				finishTest();
			}
		}.schedule(90);
		delayTestFinish(100);
	}

	@Test
	public void testNewOptionsAddedAfterTheNullOption() {
		final Select<MockSelectModel> select = createSelectWithTwoOptions().withNullOptionCaption("Please select");
		new Timer() {
			@Override
			public void run() {
				SelectItemModel newItem = new SelectItemModel();
				newItem.setCaption("hello world");
				select.getModel().getSelectItemsModel().add(0, newItem);
			}
		}.schedule(25);
		new Timer() {
			@Override
			public void run() {
				assertEquals("hello world", JQuery.select( "#select option:nth-child(2)" ).text());
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(100);
	}
	
	@Test
	public void testResetToNullValue() {
		final Select<String> select = new Select<String>().withSelectItems(SelectItems.fromStrings("a","b","c"))
					.withId("select")
					.withNullOptionCaption("Please select");
		registerApp( select );
		select.bind(Bindings.obs(this).get(TestSelectRenderer_._selectedItem));
		setSelectedItem("a");
		new Timer() {
			@Override
			public void run() {
				assertEquals("a", JQuery.select( "#select" ).val());
				setSelectedItem(null);
			}
		}.schedule(25);
		new Timer() {
			@Override
			public void run() {
				assertEquals("Please select", JQuery.select( "#select" ).val());
				finishTest();
			}
		}.schedule(90);
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

	private SelectItem<MockSelectModel> createSelectItemWithName( final String name ) {
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
