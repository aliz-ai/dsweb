
package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.io.Serializable;
import java.util.List;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.Converter;
import com.doctusoft.bean.binding.ParametricEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.BidirectionalConvertingListBinder;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.datatable.Column;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel_;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.doctusoft.dsw.client.comp.model.RowClickedEvent;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;

public class DataTable<Item> extends BaseComponent<DataTable<Item>, DataTableModel> {

	private class SelectItemToIndexConverter implements Converter<Item, Integer>, Serializable {

		@Override
		public Integer convertSource( final Item source ) {
			if (source == null) {
				return -1;
			}
			return itemsListener.getShadowList().indexOf( source );
		}

		@Override
		public Item convertTarget( final Integer target ) {
			if (target == -1) {
				return null;
			}
			return itemsListener.getShadowList().get( target );
		}
	}

	private List<Column<Item>> columns = Lists.newArrayList();

	private ListBindingListener<Item> itemsListener;

	public DataTable() {
		super( new DataTableModel() );
		withStyleClasses(BootstrapStyleClasses.TABLE_STRIPED, BootstrapStyleClasses.TABLE_BORDERED);
		Bindings.obs( model ).get( DataTableModel_._selectionMode ).addValueChangeListener( new ValueChangeListener<SelectionMode>() {

			@Override
			public void valueChanged( final SelectionMode newValue ) {
				model.getSelectedIndices().clear();
			}
		} );
	}

	public DataTable<Item> addColumn( final Column<Item> column ) {
		model.getColumns().add( column.getHeader() );
		columns.add( column );
		return this;
	}

	public DataTable<Item> removeColumn(final Column<Item> column) {
		model.getColumns().remove(column.getHeader());
		columns.remove(column);
		return this;
	}

	public DataTable<Item> bind( final ObservableValueBinding<? extends List<Item>> listBinding ) {
		if (itemsListener != null) {
			// remove previous binding
			itemsListener.remove();
		}
		itemsListener = new ListBindingListener<Item>( listBinding ) {

			@Override
			public void inserted( final ObservableList<Item> list, final int index, final Item element ) {
				model.getRows().add( index, renderRow( element ) );
			}

			@Override
			public void removed( final ObservableList<Item> list, final int index, final Item element ) {
				model.getRows().remove( index );
			}
		};
		return this;
	}

	public DataTable<Item> rowClick( final ParametricEventHandler<Item> rowClickHandler ) {
		bindEvent(DataTableModel_._rowClickedEvent, new ParametricEventHandler<Integer>() {
			@Override
			public void handle(final Integer parameter) {
				rowClickHandler.handle(new SelectItemToIndexConverter().convertTarget(parameter));
			}
		}, new Supplier<RowClickedEvent>() {
			@Override
			public RowClickedEvent get() {
				return new RowClickedEvent();
			}
		});
		return this;
	}

	/**
	 * Binds to a list of selected items. This works in both Single and Multiple selection modes. You however should not
	 * add multiple items to the bound list in Single selection mode.
	 * In Single selection mode, you'd probably prefer bindSingleSelection
	 */
	public DataTable<Item> bindSelection( final ObservableValueBinding<? extends List<Item>> selection ) {
		new BidirectionalConvertingListBinder<Item, Integer>(
				selection, new SelectItemToIndexConverter(),
				Bindings.obs( model ).get( DataTableModel_._selectedIndices ) );
		return this;
	}

	public DataTable<Item> bindSelectionMode( final ValueBinding<SelectionMode> selectionModeBinding ) {
		Bindings.bind( selectionModeBinding, Bindings.obs( model ).get( DataTableModel_._selectionMode ) );
		return this;
	}

	//	public DataTable<Item> bindSingleSelection

	/**
	 * Removes all selection
	 */
	public DataTable<Item> removeSelection() {
		model.getSelectedIndices().clear();
		return this;
	}

	protected DataTableRowModel renderRow( final Item item ) {
		DataTableRowModel rowModel = new DataTableRowModel();
		rowModel.getCells().addAll( Lists.transform( columns, new Function<Column<Item>, DataTableCellModel>() {

			@Override
			public DataTableCellModel apply( final Column<Item> input ) {
				return input.getCellModel( item );
			}
		} ) );
		return rowModel;
	}

	public DataTable<Item> withSelectionMode( final SelectionMode selectionMode ) {
		model.setSelectionMode( selectionMode );
		return this;
	}

}
