package com.doctusoft.dsw.client.comp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.util.JUnitDeferrerImpl;

public class TestAbstractSelect {
	
	@ObservableProperty
	private String selectValue;
	
	@ObservableProperty
	private List<SelectItem<String>> selectItems;
	
	@BeforeClass
	public static void setupClass() {
		new JUnitDeferrerImpl();
	}
	
	/**
	 * TODO this behaviour propbably has to be changed. See https://github.com/Doctusoft/dsweb/issues/13
	 */
	@Test
	public void testFirstOptionSelectedOnNoDefault() {
		setSelectValue(null);
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		assertEquals(null, select.getModel().getSelectedItem());
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		// there was no null option included in the select items, so the value is updated to the first selectable option
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(0), select.getModel().getSelectedItem());
		assertEquals("b", selectValue);
	}
	
	@Test
	public void testValueRetainOnFirstSelectItemsSet() {
		setSelectValue("a");
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		// as the value is not selectable yet, the selectedIndex is -1
		assertEquals(null, select.getModel().getSelectedItem());
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		// but after the selectItems is set, the selectIndex is set to 1
		assertEquals(select.getModel().getSelectItemsModel().get(1), select.getModel().getSelectedItem());
	}

	@Test
	public void testValueRetainOnFurtherSelectItemsSet() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		setSelectValue("a");
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(1), select.getModel().getSelectedItem());
		// set the the same select items again, but with new instances, and in a different order, keeping the value intact
		select.setSelectItems(SelectItems.fromStrings("a", "b", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(0), select.getModel().getSelectedItem());
		assertEquals("a", getSelectValue());

	}

	@Test
	public void testValueRetainOnSelectItemsBindingChanged() {
		setSelectValue("a");
		setSelectItems(null);
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue))
					.bindSelectItems(Bindings.obs(this).get(TestAbstractSelect_._selectItems));
		// as the value is not selectable yet, the selectedIndex is -1
		assertEquals(null, select.getModel().getSelectedItem());
		setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		// but after the selectItems is set, the selectIndex is set to 1
		assertEquals(select.getModel().getSelectItemsModel().get(1), select.getModel().getSelectedItem());
	}

	@Test
	public void testSelectedItemRemoved() {
		List<SelectItem<String>> defaultSelectItems = SelectItems.fromStrings("b", "a", "c");
		ObservableList<SelectItem<String>> selectItems = new ObservableList<SelectItem<String>>(defaultSelectItems);
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		select.bindSelectItems(Bindings.obs(selectItems));
		select.setValue("a");
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(1), select.getModel().getSelectedItem());
		selectItems.clear();
		JUnitDeferrerImpl.fireScheduledRunnables();
		// as the selectable items are removed, the user would see no value, so the model value has to be null.
		assertEquals(null, select.getValue());
		assertEquals(null, select.getModel().getSelectedItem());
		selectItems.addAll(defaultSelectItems);
		JUnitDeferrerImpl.fireScheduledRunnables();
		// the first item gets selected, to properly reflect what the user sees.
		assertEquals("b", select.getValue());
	}
	
	@Test
	public void testValueUpdatedToNull() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		setSelectValue("a");
		select.setSelectItems(SelectItems.fromStrings(null, "b", "a", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(2), select.getModel().getSelectedItem());
		setSelectValue(null);
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(0), select.getModel().getSelectedItem());
		assertEquals(null, selectValue);
	}

	@Test
	public void testInvalidValueSet() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		setSelectValue("a");
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(1), select.getModel().getSelectedItem());
		setSelectValue(null);
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(select.getModel().getSelectItemsModel().get(0), select.getModel().getSelectedItem());
		assertEquals("b", selectValue);
	}
	
	@Test
	public void testNullValueAllowedWithNulOption() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue))
					.withNullOptionCaption("Please select");
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(null, getSelectValue());
	}
	
	@Test
	public void testSetNullValueAndAllow() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals("b", getSelectValue());
		// now set the null value and allow it
		setSelectValue(null);
		select.withNullOptionCaption("Please select");
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(null, getSelectValue());
	}
	
	@Test
	public void testRemoveNullValueWhenDisallowed() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue))
				.withNullOptionCaption("Please select");
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(null, getSelectValue());
		select.withNullOptionCaption(null);
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals("b", getSelectValue());
	}
	
	@Test
	public void testResetToNullValue() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue))
				.withNullOptionCaption("Please select");
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		setSelectValue("a");
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals("a", getSelectValue());
		setSelectValue(null);
		JUnitDeferrerImpl.fireScheduledRunnables();
		assertEquals(null, getSelectValue());
		assertEquals(null, select.getModel().getSelectedItem());
	}
}
