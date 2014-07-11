package com.doctusoft.dsw.client.comp;

import java.util.List;

import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.datatable.Column;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DataTable<Item> extends BaseComponent<DataTable<Item>, DataTableModel> {
	
	private List<Column<Item>> columns = Lists.newArrayList();
	
	public DataTable() {
		super(new DataTableModel());
	}
	
	public DataTable<Item> bind(ObservableValueBinding<? extends List<Item>> listBinding) {
		new ListBindingListener<Item>(listBinding) {
			@Override
			public void inserted(ObservableList<Item> list, int index, Item element) {
				model.getRows().add(index, renderRow(element));
			}
			@Override
			public void removed(ObservableList<Item> list, int index, Item element) {
				model.getRows().remove(index);
			}
		};
		return this;
	}
	
	protected DataTableRowModel renderRow(final Item item) {
		DataTableRowModel rowModel = new DataTableRowModel();
		rowModel.getCells().addAll(Lists.transform(columns, new Function<Column<Item>, DataTableCellModel>() {
			@Override
			public DataTableCellModel apply(Column<Item> input) {
				DataTableCellModel cellModel = new DataTableCellModel();
				String stringContent = input.getStringContent(item);
				cellModel.setTextContent(stringContent);
				if (stringContent == null) {
					HasComponentModel component = input.getComponent(item);
					if (component != null) {
						cellModel.setComponent(component.getComponentModel());
					}
				}
				return cellModel;
			}
		}));
		return rowModel;
	}

	public DataTable<Item> addColumn(Column<Item> column) {
		model.getColumns().add(column.getHeader());
		columns.add(column);
		return this;
	}
}
