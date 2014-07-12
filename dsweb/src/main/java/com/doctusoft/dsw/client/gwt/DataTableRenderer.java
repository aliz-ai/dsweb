package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel_;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.google.common.collect.Lists;
import com.google.gwt.core.shared.GWT;
import com.xedge.jquery.client.JQuery;

public class DataTableRenderer extends BaseComponentRenderer {
	
	private List<JQuery> rows = Lists.newArrayList();
	
	private RendererFactory<JQuery> rendererFactory = GWT.create(RendererFactory.class);

	private DataTableModel model;
	
	public DataTableRenderer(DataTableModel model) {
		super(JQuery.select("<table class=\"display\"/>"), model);
		this.model = model;
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
		new ListBindingListener<Integer>(Bindings.obs(model).get(DataTableModel_._selectedIndices)) {
			@Override
			public void inserted(ObservableList<Integer> list, int index, Integer element) {
				rows.get(element).addClass("selected");
			}
			@Override
			public void removed(ObservableList<Integer> list, int index, Integer element) {
				rows.get(element).removeClass("selected");
			}
		};
		install(widget);
	}
	
	protected JQuery renderRow(DataTableRowModel rowModel) {
		JQuery row = JQuery.select("<tr/>");
		for (DataTableCellModel cellModel : rowModel.getCells()) {
			JQuery cell = JQuery.select("<td/>").appendTo(row);
			String textContent = cellModel.getTextContent();
			if (textContent != null) {
				cell.text(textContent);
			} else {
				BaseComponentModel component = cellModel.getComponent();
				if (component != null) {
					cell.append(rendererFactory.getRenderer(component).getWidget());
				}
			}
		}
		return row;
	}
	
	protected void rowClicked(JQuery row) {
		if (model.getSelectionMode() == null)
			return;
		switch (model.getSelectionMode()) {
		case Single: {
			int rowIndex = row.parent().children().index(row.get(0));
			ObservableList<Integer> selectedIndices = model.getSelectedIndices();
			if (selectedIndices.contains(rowIndex)) {
				selectedIndices.clear();
			} else {
				selectedIndices.clear();
				selectedIndices.add(rowIndex);
			}
		} break;
		case Multiple: {
			int rowIndex = row.parent().children().index(row.get(0));
			ObservableList<Integer> selectedIndices = model.getSelectedIndices();
			if (selectedIndices.contains(rowIndex)) {
				selectedIndices.remove((Object)rowIndex);	// not by index but by value
			} else {
				selectedIndices.add(rowIndex);
			}
		} break;
		default:
			break;
		}
	}

	private native void install(JQuery target) /*-{
		var that = this;
		target.dataTable();
		target.find("tbody").on("click", "tr", function() {
			that.@com.doctusoft.dsw.client.gwt.DataTableRenderer::rowClicked(Lcom/xedge/jquery/client/JQuery;)($wnd.jQuery(this));
		});
	}-*/;
}
