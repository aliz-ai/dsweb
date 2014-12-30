
package com.doctusoft.dsw.client.gwt;

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


import java.util.List;

import com.doctusoft.bean.ListenerRegistration;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.datatable.OrderingDirection;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel_;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel_;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel_;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.doctusoft.dsw.client.util.DeferredFactory;
import com.doctusoft.dsw.client.util.DeferredRunnable;
import com.google.common.collect.Lists;
import com.google.gwt.core.shared.GWT;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.JQuery.EventType;

public class DataTableRenderer extends BaseComponentRenderer {

	private final List<JQuery> rows = Lists.newArrayList();

	private final RendererFactory<JQuery> rendererFactory = GWT.create( RendererFactory.class );

	private final DataTableModel model;

	private DeferredRunnable deferredRunnable = null;
	private DeferredChangeListener deferredChangeListeners = new DeferredChangeListener();

	private List<ListenerRegistration> headerListenerRegistrations;

	public DataTableRenderer( final DataTableModel model ) {
		super( JQuery.select( "<table class=\"table\"/>" ), model );
		this.model = model;
		// apply columns, no change supported currently
		renderHeaders();

		final JQuery tbody = JQuery.select( "<tbody/>" ).appendTo( widget );
		new ListBindingListener<DataTableRowModel>( Bindings.obs( model ).get( DataTableModel_._rows ) ) {

			@Override
			public void inserted( final ObservableList<DataTableRowModel> list, final int index, final DataTableRowModel element ) {
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
			}

			@Override
			public void removed( final ObservableList<DataTableRowModel> list, final int index, final DataTableRowModel element ) {
				rows.get( index ).remove();
				rows.remove( index );
			}
		};
		new ListBindingListener<Integer>( Bindings.obs( model ).get( DataTableModel_._selectedIndices ) ) {

			@Override
			public void inserted( final ObservableList<Integer> list, final int index, final Integer element ) {
				if (rows.size() > element){ //temporary solution, FIXME gabor-farkas https://github.com/Doctusoft/dsweb/issues/20
					rows.get( element ).addClass( "selected" );
				}
			}

			@Override
			public void removed( final ObservableList<Integer> list, final int index, final Integer element ) {
				if (rows.size() > element){ //temporary solution, FIXME gabor-farkas https://github.com/Doctusoft/dsweb/issues/20
					rows.get( element ).removeClass( "selected" );
				}
			}
		};

		new ListBindingListener<DataTableColumnModel>(Bindings.obs(model).get(DataTableModel_._columns)) {

			@Override
			public void inserted(final ObservableList<DataTableColumnModel> list, final int index, final DataTableColumnModel element) {
				deferredRunnable = DeferredFactory.defer(deferredRunnable, deferredChangeListeners);
			}

			@Override
			public void removed(final ObservableList<DataTableColumnModel> list, final int index, final DataTableColumnModel element) {
				deferredRunnable = DeferredFactory.defer(deferredRunnable, deferredChangeListeners);
			}
		};

		install( widget );
	}

	protected JQuery renderRow( final DataTableRowModel rowModel ) {
		JQuery row = JQuery.select( "<tr/>" );
		for (DataTableCellModel cellModel : rowModel.getCells()) {
			final JQuery cell = JQuery.select( "<td/>" ).appendTo( row );
			String textContent = cellModel.getTextContent();
			if (textContent != null) {
				cell.text( textContent );
				DataTableCellModel_._textContent.addChangeListener( cellModel, new ValueChangeListener<String>() {

					@Override
					public void valueChanged( final String newValue ) {
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

	protected void rowClicked( final JQuery row ) {
		int rowIndex = row.parent().children().index( row.get( 0 ) );
		if (model.getRowClickedEvent().isHasListeners()) {
			model.getRowClickedEvent().fire( rowIndex );
		}
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

	private class DeferredChangeListener implements Runnable {

		@Override
		public void run() {
			rerenderAllHeaders();
			rerenderAllRows();

			deferredRunnable = null;
		}
	}

	protected void rerenderAllRows() {
		widget.find("tbody").remove();
		rows.clear();
		final JQuery tbody = JQuery.select( "<tbody/>" ).appendTo( widget );

		for (DataTableRowModel dataTableRowModel : model.getRows()) {
			JQuery renderRow = renderRow(dataTableRowModel);

			tbody.append(renderRow);
			rows.add(renderRow);
		}

	}

	protected void rerenderAllHeaders() {
		headerListenerRegistrations.clear();
		widget.find("thead").remove();
		renderHeaders();
	}

	protected void renderHeaders() {
		headerListenerRegistrations = Lists.newArrayList();
		JQuery headerRow = JQuery.select( "<tr>" ).appendTo( JQuery.select( "<thead>" ).appendTo( widget ) );

		for (final DataTableColumnModel columnModel : model.getColumns()) {
			JQuery th = JQuery.select( "<th>" + columnModel.getTitle() + "</th>" ).appendTo( headerRow );
			if (columnModel.isOrderable()) {
				th.addClass("orderable");
				final JQuery icon = JQuery.select("<i class='ordering-icon'/>").appendTo(th);
				ListenerRegistration orderingListener = addChangeListenerAndApply(DataTableColumnModel_._orderingDirection, columnModel, new ValueChangeListener<OrderingDirection>() {
					@Override
					public void valueChanged(final OrderingDirection newValue) {
						if (newValue == null) {
							icon.attr("class", "ordering-icon"); //remove all other classes
						} else {
							icon.attr("class", "ordering-icon " + newValue.name().toLowerCase() + " " + (((newValue == OrderingDirection.Ascending)?BootstrapIcon.ICON_ARROW_DOWN:BootstrapIcon.ICON_ARROW_UP)).getClassName());
						}
					}
				});

				headerListenerRegistrations.add(orderingListener);
			}
			bindEvent(th, EventType.click, Bindings.obs(columnModel).get(DataTableColumnModel_._click), new EventTriggerer<ComponentEvent>() {
				@Override
				public void triggerEvent(final ComponentEvent event, final com.xedge.jquery.client.JQEvent jqEvent) {
					if (columnModel.isOrderable()) {
						event.fire();
					}
				};
			});
		}
	}

	private native void destroy(final JQuery target) /*-{
		target.DataTable().destroy();
	}-*/;

	private native void install(final JQuery target) /*-{
		var that = this;
		setTimeout(function () {
			// row click listener
			target.find("tbody").on("mousedown", "tr", function() {
				that.@com.doctusoft.dsw.client.gwt.DataTableRenderer::rowClicked(Lcom/xedge/jquery/client/JQuery;)($wnd.jQuery(this));
			});
		}, 1);
	}-*/;
}
