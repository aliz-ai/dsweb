
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.xedge.jquery.client.JQuery;

public class TestDataTableRenderer extends AbstractDswebTest {
	
	@Test
	public void testRowContentWithAdditionsAndRemovals() {
		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		ObservableList<DataTableRowModel> rows = createRows();
		DataTableModel model = dataTable.getModel();
		model.setRows( rows );
		registerApp( dataTable );
		JQuery firstCell = JQuery.select( "tbody>tr>td:first" );
		assertEquals( "00", firstCell.text() );
		JQuery lastCell = JQuery.select( "tbody>tr>td:last" );
		assertEquals( "11", lastCell.text() );
		model.getRows().remove( 1 );
		lastCell = JQuery.select( "tbody>tr>td:last" );
		assertEquals( "01", lastCell.text() );
		model.getRows().add( createRow( 4 ) );
		lastCell = JQuery.select( "tbody>tr>td:last" );
		assertEquals( "41", lastCell.text() );
	}
	
	private ObservableList<DataTableRowModel> createRows() {
		ObservableList<DataTableRowModel> rows = new ObservableList<DataTableRowModel>();
		rows.add( createRow( 0 ) );
		rows.add( createRow( 1 ) );
		return rows;
	}
	
	private DataTableRowModel createRow( Integer index ) {
		DataTableRowModel rowModel = new DataTableRowModel();
		ObservableList<DataTableCellModel> cells = new ObservableList<DataTableCellModel>();
		cells.add( createCellModel( index.toString() + "0" ) );
		cells.add( createCellModel( index.toString() + "1" ) );
		rowModel.setCells( cells );
		return rowModel;
	}
	
	private DataTableCellModel createCellModel( String content ) {
		DataTableCellModel cellModel = new DataTableCellModel();
		cellModel.setTextContent( content );
		return cellModel;
	}
}
