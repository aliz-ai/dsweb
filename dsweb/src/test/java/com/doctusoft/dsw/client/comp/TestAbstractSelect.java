package com.doctusoft.dsw.client.comp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;

public class TestAbstractSelect {
	
	@ObservableProperty
	private String selectValue;
	
	@ObservableProperty
	private List<SelectItem<String>> selectItems;
	
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
}
