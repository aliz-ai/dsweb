package com.doctusoft.dsw.client.comp.datatable;

import java.io.Serializable;
import java.util.Map;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ListenerRegistration;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.FilteredObservableListBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel_;
import com.doctusoft.dsw.client.comp.model.DataTableModel_;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

/**
 * An ordering behaviour for {@link DataTable}-s that supports at most one column to be ordered.
 * To make a column in the datatable orderable, call .orderable() when building it.
 * This behaviour can be attached after having built the datatable, eg: <code> new SingleColumnOrderingBehaviour(dataTable).bind(...); </code>
 */
public class SingleColumnOrderingBehaviour implements Serializable {
	
	private Map<DataTableColumnModel, ListenerRegistration> clickListeners = Maps.newHashMap();
	
	@ObservableProperty
	private SingleDataTableOrdering ordering = null;

	public SingleColumnOrderingBehaviour(final DataTable<?> dataTable) {
		new FilteredObservableListBinding<DataTableColumnModel>(Bindings.obs(dataTable.getModel()).get(DataTableModel_._columns), new ColumnOrderablePredicate()) {
			@Override
			protected void inserted(ObservableList<DataTableColumnModel> list, int index, DataTableColumnModel element) {
				ListenerRegistration listenerRegistration = ComponentEvent.bindEvent(element, DataTableColumnModel_._click, new ColumnClickEventHandler(element, list));
				clickListeners.put(element, listenerRegistration);
			}
			@Override
			protected void removed(ObservableList<DataTableColumnModel> list,
					int index, DataTableColumnModel element) {
				ListenerRegistration listenerRegistration = clickListeners.remove(element);
				if (listenerRegistration != null) {
					listenerRegistration.removeHandler();
				}
			}
		};
		SingleColumnOrderingBehaviour_._ordering.addChangeListener(this, new ValueChangeListener<SingleDataTableOrdering>() {
			@Override
			public void valueChanged(SingleDataTableOrdering newValue) {
				// clear all ordering information on columns
				ObservableList<DataTableColumnModel> columns = dataTable.getComponentModel().getColumns();
				for (DataTableColumnModel columnModel : columns) {
					if (columnModel.getOrdering() != null) {
						columnModel.setOrdering(null);
					}
				}
				if (newValue != null) {
					DataTableColumnModel columnModel = columns.get(newValue.getOrderColumnIndex());
					if (columnModel.isOrderable()) {
						columnModel.setOrdering(newValue.getDirection());
					}
				}
			}
		});
	}
	
	public SingleColumnOrderingBehaviour bind(ValueBinding<SingleDataTableOrdering> orderingBinding) {
		Bindings.bind(orderingBinding, Bindings.obs(this).get(SingleColumnOrderingBehaviour_._ordering));
		return this;
	}
	
	private class ColumnClickEventHandler implements EmptyEventHandler {
		private DataTableColumnModel dataTableColumnModel;
		private ObservableList<DataTableColumnModel> columnDefList;
		public ColumnClickEventHandler(DataTableColumnModel dataTableColumnModel, ObservableList<DataTableColumnModel> columnDefList) {
			this.dataTableColumnModel = dataTableColumnModel;
			this.columnDefList = columnDefList;
		}
		@Override
		public void handle() {
			int index = columnDefList.indexOf(dataTableColumnModel);
			Preconditions.checkState(index >= 0, "The given column is not actually a member of the column definition list. Something's messy");
			if (ordering != null && ordering.getOrderColumnIndex() == index) {
				// toggle direction
				setOrdering(new SingleDataTableOrdering(ordering.getOrderColumnIndex(), ordering.getDirection().invert()));
			} else {
				setOrdering(new SingleDataTableOrdering(index, OrderingDirection.Ascending));
			}
		}
	}
	
	private static class ColumnOrderablePredicate implements Predicate<DataTableColumnModel> {
		@Override
		public boolean apply(DataTableColumnModel input) {
			return input.isOrderable();
		}
	}
}
