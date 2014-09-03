
package com.doctusoft.dsw.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.junit.Test;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.xedge.jquery.client.JQuery;

public class TestDataTableRenderer extends AbstractDswebTest {
	
	@Test
	public void testRowContentWithAdditionsRemovalsAndChanges() {
		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		ObservableList<DataTableRowModel> rows = createRows();
		DataTableModel model = dataTable.getModel();
		model.setRows( rows );
		registerApp( dataTable );
		JQuery firstCell = JQuery.select( "tbody>tr>td" ).first();
		assertEquals( "00", firstCell.text() );
		JQuery lastCell = JQuery.select( "tbody>tr>td" ).last();
		assertEquals( "11", lastCell.text() );
		model.getRows().remove( 1 );
		lastCell = JQuery.select( "tbody>tr>td" ).last();
		assertEquals( "01", lastCell.text() );
		model.getRows().add( createRow( 4 ) );
		lastCell = JQuery.select( "tbody>tr>td" ).last();
		assertEquals( "41", lastCell.text() );
		model.getRows().get( 1 ).getCells().get( 1 ).setTextContent( "changed" );
		lastCell = JQuery.select( "tbody>tr>td" ).last();
		assertEquals( "changed", lastCell.text() );
	}
	
	@Test
	//TODO vmiert unit teszteknek nem mukodnek a headerek
	public void testColumnHeaders() {
		//		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		//		DataTableModel model = dataTable.getModel();
		//		ObservableList<DataTableColumnModel> headers = createColumnHeaders();
		//		model.setColumns( headers );
		//		model.setRows( createRows() );
		//		registerApp( dataTable );
		//		dumpRoot();
	}
	
	@Test
	public void testRowClickedEvent() {
		//		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		//		ObservableList<DataTableRowModel> rows = createRows();
		//		DataTableModel model = dataTable.getModel();
		//		model.setRows( rows );
		//		JQuery jqFirstRow = JQuery.select( "table tbody tr" ).last();
		//		final BooleanWrapper clickFiredOnModel = new BooleanWrapper( false );
		//		dataTable.rowClick( new ParametricEventHandler<String>() {
		//			
		//			@Override
		//			public void handle( String parameter ) {
		//				clickFiredOnModel.setValue( true );
		//				assertEquals( "00", parameter );
		//			}
		//		} );
		//		
		//		jqFirstRow.mousedown();
		//		new Timer() {
		//			
		//			@Override
		//			public void run() {
		//				assertTrue( clickFiredOnModel.isValue() );
		//				finishTest();
		//			}
		//		}.schedule( 50 );
		//		delayTestFinish( 100 );
		
		//TODO nem suti el jol az esemenyt tesztkornyezetben
	}
	
	private ObservableList<DataTableColumnModel> createColumnHeaders() {
		ObservableList<DataTableColumnModel> columns = new ObservableList<DataTableColumnModel>();
		columns.add( createColumnModel( "aaa" ) );
		return columns;
	}
	
	private DataTableColumnModel createColumnModel( String title ) {
		DataTableColumnModel columnModel = new DataTableColumnModel();
		columnModel.setTitle( title );
		return columnModel;
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
	
	@Getter
	@Setter
	@AllArgsConstructor
	private static class BooleanWrapper {
		
		private boolean value;
	}
}
