package com.doctusoft.dsw.client.comp.datatable;

import lombok.Getter;

import com.doctusoft.bean.Property;
import com.doctusoft.bean.binding.Converter;

@Getter
public class PropertyColumnDescriptor<Item, Value> extends ColumnDescriptor<Item> {

	private Property<Item, Value> property;

	private Converter<Value, String> converter;

	public PropertyColumnDescriptor(final String title, final Property<Item, Value> property) {
		super(title);

		this.property = property;
	}

	public PropertyColumnDescriptor<Item, Value> withConverter(final Converter<Value, String> converter) {
		this.converter = converter;
		return this;
	}

}
