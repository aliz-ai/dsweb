package com.doctusoft.dsw.client.comp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;

public class TestAbstractSelect {
	
	@ObservableProperty
	private String selectValue;
	
	@ObservableProperty
	private List<SelectItem<String>> selectItems;
	
	/**
	 * TODO this behaviour propbably has to be changed. See https://github.com/Doctusoft/dsweb/issues/13
	 */
	@Test
	public void testFirstOptionSelectedOnNoDefault() {
		setSelectValue(null);
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		assertEquals(-1, select.getModel().getSelectedIndex());
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		// there was no null option included in the select items, so the value is updated to the first selectable option
		assertEquals(0, select.getModel().getSelectedIndex());
		assertEquals("b", selectValue);
	}
	
	@Test
	public void testValueRetainOnSelectItemsSet() {
		setSelectValue("a");
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		// as the value is not selectable yet, the selectedIndex is -1
		assertEquals(-1, select.getModel().getSelectedIndex());
		select.setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		// but after the selectItems is set, the selectIndex is set to 1
		assertEquals(1, select.getModel().getSelectedIndex());
	}

	@Test
	public void testValueRetainOnSelectItemsBindingChanged() {
		setSelectValue("a");
		setSelectItems(null);
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue))
					.bindSelectItems(Bindings.obs(this).get(TestAbstractSelect_._selectItems));
		// as the value is not selectable yet, the selectedIndex is -1
		assertEquals(-1, select.getModel().getSelectedIndex());
		setSelectItems(SelectItems.fromStrings("b", "a", "c"));
		// but after the selectItems is set, the selectIndex is set to 1
		assertEquals(1, select.getModel().getSelectedIndex());
	}

	@Test
	public void testSelectedItemRemoved() {
		List<SelectItem<String>> defaultSelectItems = SelectItems.fromStrings("b", "a", "c");
		ObservableList<SelectItem<String>> selectItems = new ObservableList<SelectItem<String>>(defaultSelectItems);
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		select.bindSelectItems(Bindings.obs(selectItems));
		select.setValue("a");
		assertEquals(1, select.getModel().getSelectedIndex());
		selectItems.clear();
		// as the selectable items are removed, the user would see no value, so the model value has to be null.
		assertEquals(null, select.getValue());
		assertEquals(-1, select.getModel().getSelectedIndex());
		selectItems.addAll(defaultSelectItems);
		// the first item gets selected, to properly reflect what the user sees.
		assertEquals("b", select.getValue());
	}
	
	@Test
	public void testValueUpdatedToNull() {
		Select<String> select = new Select<String>().bind(Bindings.obs(this).get(TestAbstractSelect_._selectValue));
		setSelectValue("a");
		select.setSelectItems(SelectItems.fromStrings(null, "b", "a", "c"));
		assertEquals(2, select.getModel().getSelectedIndex());
		setSelectValue(null);
		assertEquals(0, select.getModel().getSelectedIndex());
		assertEquals(null, selectValue);
	}

}
