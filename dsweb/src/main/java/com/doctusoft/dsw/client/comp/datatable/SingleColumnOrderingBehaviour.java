package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.FilteredObservableListBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel_;
import com.google.common.base.Predicate;

/**
 * An ordering behaviour for {@link DataTable}-s that supports at most one column to be ordered.
 */
public class SingleColumnOrderingBehaviour {

	public SingleColumnOrderingBehaviour(DataTable<?> dataTable) {
		new FilteredObservableListBinding<DataTableColumnModel>(Bindings.obs(dataTable.getModel()).get(DataTableModel_._columns), new ColumnOrderablePredicate()) {
			@Override
			protected void inserted(ObservableList<DataTableColumnModel> list,
					int index, DataTableColumnModel element) {
				
			}
			@Override
			protected void removed(ObservableList<DataTableColumnModel> list,
					int index, DataTableColumnModel element) {
				
			}
		};
	}
	
	private static class ColumnOrderablePredicate implements Predicate<DataTableColumnModel> {
		@Override
		public boolean apply(DataTableColumnModel input) {
			return input.isOrderable();
		}
	}
}
