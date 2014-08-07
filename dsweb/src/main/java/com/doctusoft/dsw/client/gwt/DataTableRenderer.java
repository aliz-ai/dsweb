
package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel_;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel_;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.google.common.collect.Lists;
import com.google.gwt.core.shared.GWT;
import com.xedge.jquery.client.JQuery;

public class DataTableRenderer extends BaseComponentRenderer {
	
	private final List<JQuery> rows = Lists.newArrayList();
	
	private final RendererFactory<JQuery> rendererFactory = GWT.create( RendererFactory.class );
	
	private final DataTableModel model;
	
	public DataTableRenderer( DataTableModel model ) {
		super( JQuery.select( "<table class=\"table table-striped table-bordered\"/>" ), model );
		this.model = model;
		// apply columns, no change supported currently
		JQuery headerRow = JQuery.select( "<tr>" ).appendTo( JQuery.select( "<thead>" ).appendTo( widget ) );
		for (DataTableColumnModel columnModel : model.getColumns()) {
			JQuery.select( "<th>" + columnModel.getTitle() + "</th>" ).appendTo( headerRow );
		}
		final JQuery tbody = JQuery.select( "<tbody/>" ).appendTo( widget );
		new ListBindingListener<DataTableRowModel>( Bindings.obs( model ).get( DataTableModel_._rows ) ) {
			
			@Override
			public void inserted( ObservableList<DataTableRowModel> list, int index, DataTableRowModel element ) {
				JQuery row = renderRow( element );
				if (index == 0) {
					// insert as first
					tbody.prepend( row );
				}
				else if (index == rows.size()) {
					// insert as last
					tbody.append( row );
				}
				else {
					tbody.insertBefore( rows.get( index ) );
				}
				rows.add( index, row );
				/*
				Deferred.defer(DataTableRenderer.this, "reinit", new Runnable() {
					@Override
					public void run() {
						reinit();
					}
				});
				*/
			}
			
			@Override
			public void removed( ObservableList<DataTableRowModel> list, int index, DataTableRowModel element ) {
				rows.get( index ).remove();
				rows.remove( index );
			}
		};
		new ListBindingListener<Integer>( Bindings.obs( model ).get( DataTableModel_._selectedIndices ) ) {
			
			@Override
			public void inserted( ObservableList<Integer> list, int index, Integer element ) {
				rows.get( element ).addClass( "selected" );
			}
			
			@Override
			public void removed( ObservableList<Integer> list, int index, Integer element ) {
				rows.get( element ).removeClass( "selected" );
			}
		};
		install( widget );
	}
	
	protected JQuery renderRow( DataTableRowModel rowModel ) {
		JQuery row = JQuery.select( "<tr/>" );
		for (DataTableCellModel cellModel : rowModel.getCells()) {
			final JQuery cell = JQuery.select( "<td/>" ).appendTo( row );
			String textContent = cellModel.getTextContent();
			if (textContent != null) {
				cell.text( textContent );
				DataTableCellModel_._textContent.addChangeListener( cellModel, new ValueChangeListener<String>() {
					
					@Override
					public void valueChanged( String newValue ) {
						cell.text( newValue );
					}
				} );
			}
			else {
				BaseComponentModel component = cellModel.getComponent();
				if (component != null) {
					cell.append( rendererFactory.getRenderer( component ).getWidget() );
				}
			}
		}
		return row;
	}
	
	protected void rowClicked( JQuery row ) {
		int rowIndex = row.parent().children().index( row.get( 0 ) );
		model.getRowClickedEvent().fire( rowIndex );
		if (model.getSelectionMode() == null) {
			return;
		}
		switch (model.getSelectionMode()) {
			case Single: {
				ObservableList<Integer> selectedIndices = model.getSelectedIndices();
				if (selectedIndices.contains( rowIndex )) {
					selectedIndices.clear();
				}
				else {
					selectedIndices.clear();
					selectedIndices.add( rowIndex );
				}
			}
				break;
			case Multiple: {
				ObservableList<Integer> selectedIndices = model.getSelectedIndices();
				if (selectedIndices.contains( rowIndex )) {
					selectedIndices.remove( (Object) rowIndex );	// not by index but by value
				}
				else {
					selectedIndices.add( rowIndex );
				}
			}
				break;
			default:
				break;
		}
	}
	
	private native int setPageNum() /*-{
		
	}-*/;
	
	protected void reinit() {
		widget.find(".dataTables_empty").parent().remove();
		JQuery tempContainer = JQuery.select("<div/>").appendTo("body");
		JQuery rows = widget.find("tbody tr").appendTo(tempContainer);
		destroy(widget);
		tempContainer.find("tr").first().appendTo(widget.find("tbody"));
		install(widget);
		tempContainer.remove();
	}
	
	private native void destroy(JQuery target) /*-{
		target.DataTable().destroy();
	}-*/;
	
	private native void install(JQuery target) /*-{
		var that = this;
		setTimeout(function () {
//			if ($wnd.$.fn.dataTable.isDataTable(target))	// already installed
//				return;
//			var that = this;
//			var table = target.DataTable({
//				//lengthMenu : [[1, 2, -1], [1, 2, "All"]],
//				//lengthChange: true,
//				ordering : false,
//				searching : false
//			 });
//			target.on( 'page.dt',   function () {
//				//alert(table.page.info().page + 1);
//				//alert(table.page.info().recordsTotal);
//			});
			// row click listener
			target.find("tbody").on("mousedown", "tr", function() {
				that.@com.doctusoft.dsw.client.gwt.DataTableRenderer::rowClicked(Lcom/xedge/jquery/client/JQuery;)($wnd.jQuery(this));
			});
		}, 1);
	}-*/;
}
