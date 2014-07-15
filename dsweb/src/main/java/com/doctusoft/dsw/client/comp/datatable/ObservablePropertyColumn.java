package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.Converter;
import com.doctusoft.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel_;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;

public class ObservablePropertyColumn<Item, Value> implements Column<Item> {
	
	private DataTableColumnModel columnModel;
	private ObservableProperty<Item, Value> property;
	private Converter<Value, String> converter = null;

	/**
	 * You are encouraged to use {@link Columns#obs(String, ObservableProperty)} instead 
	 */
	public ObservablePropertyColumn(String title, ObservableProperty<Item, Value> property) {
		this.property = property;
		columnModel = new DataTableColumnModel();
		columnModel.setTitle(title);
	}
	
	public DataTableColumnModel getHeader() {
		return columnModel; 
	}
	
	public ObservablePropertyColumn<Item, Value> format(Converter<Value, String> converter) {
		this.converter = converter;
		return this;
	}
	
	@Override
	public DataTableCellModel getCellModel(Item item) {
		DataTableCellModel cellModel = new DataTableCellModel();
		if (item == null) {
			cellModel.setTextContent("");
		} else {
			ObservableChainedValueBindingBuilder<Value> sourceBinding = Bindings.obs(item).get(property);
			ObservableValueBinding<String> sourceStringBinding = null;
			if (converter == null) {
				sourceStringBinding = sourceBinding.convert(new ToStringConverter<Value>());
			} else {
				sourceStringBinding = sourceBinding.convert(converter);
			}
			Bindings.bind(sourceStringBinding, Bindings.on(cellModel).get(DataTableCellModel_._textContent));
		}
		return cellModel;
	}
	

	private static class ToStringConverter<T> implements Converter<T, String> {
		@Override
		public String convertSource(T source) {
			if (source == null)
				return "";
			return source.toString();
		}
		@Override
		public T convertTarget(String target) {
			throw new UnsupportedOperationException();
		}
	}
}
