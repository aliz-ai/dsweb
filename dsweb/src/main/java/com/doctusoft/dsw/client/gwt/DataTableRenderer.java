package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel_;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQuery;

public class DataTableRenderer extends BaseComponentRenderer {
	
	private List<JQuery> rows = Lists.newArrayList();
	
	public DataTableRenderer(DataTableModel model) {
		super(JQuery.select("<table class=\"display\"/>"), model);
		// apply columns, no change supported currently
		JQuery headerRow = JQuery.select("<tr>").appendTo(JQuery.select("<thead>").appendTo(widget));
		for (DataTableColumnModel columnModel : model.getColumns()) {
			JQuery.select("<th>" + columnModel.getTitle() + "</th>").appendTo(headerRow);
		}
		final JQuery tbody = JQuery.select("<tbody/>").appendTo(widget);
		new ListBindingListener<DataTableRowModel>(Bindings.obs(model).get(DataTableModel_._rows)) {
			@Override
			public void inserted(ObservableList<DataTableRowModel> list, int index, DataTableRowModel element) {
				JQuery row = renderRow(element);
				if (index == 0) {
					// insert as first
					tbody.prepend(row);
				} else if (index == rows.size()) {
					// insert as last
					tbody.append(row);
				} else {
					tbody.insertBefore(rows.get(index));
				}
				rows.add(index, row);
			}
			@Override
			public void removed(ObservableList<DataTableRowModel> list, int index, DataTableRowModel element) {
				rows.get(index).remove();
				rows.remove(index);
			}
		};
		install(widget);
	}
	
	protected JQuery renderRow(DataTableRowModel rowModel) {
		JQuery row = JQuery.select("<tr/>");
		for (DataTableCellModel cellModel : rowModel.getCells()) {
			JQuery.select("<td>" + cellModel.getTextContent() + "</td>").appendTo(row);
		}
		return row;
	}

	private static native void install(JQuery target) /*-{
		target.dataTable();
	}-*/;
}
