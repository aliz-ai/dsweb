package com.doctusoft.dsw.client.gwt;

import java.math.BigDecimal;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.InputNumber;
import com.doctusoft.dsw.client.comp.model.InputNumberModel;
import com.xedge.jquery.client.JQuery;

public class TestInputNumberRenderer extends AbstractDswebTest {

	@Test
	public void testValue() {
		InputNumber inputNumber = new InputNumber().withId("in");
		InputNumberModel model = inputNumber.getModel();
		model.setValue(new BigDecimal(5));
		registerApp(inputNumber);
		dumpRoot();
		JQuery jqInput = JQuery.select("#in");
		assertEquals("5", jqInput.val());
		model.setValue(new BigDecimal(10));
		assertEquals("10", jqInput.val());
		jqInput.val("600");
		jqInput.change();
		assertEquals(new BigDecimal("600"), model.getValue());
	}

	@Test
	public void testPlaceHolder() {
		final InputNumber inputNumber = new InputNumber().withId("input").withPlaceHolder("ph");
		registerApp(inputNumber);
		JQuery jqInput = JQuery.select("#input");
		String actualPlaceholder = jqInput.attr("placeholder");
		assertEquals("ph", actualPlaceholder);
		inputNumber.withPlaceHolder("changed");
		actualPlaceholder = jqInput.attr("placeholder");
		assertEquals("changed", actualPlaceholder);
	}

}
