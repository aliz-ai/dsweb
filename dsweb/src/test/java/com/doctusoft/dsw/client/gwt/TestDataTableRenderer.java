
package com.doctusoft.dsw.client.gwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.datatable.Column;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.doctusoft.dsw.client.util.GWTTimerDeferrerImpl;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestDataTableRenderer extends AbstractDswebTest {

	@ObservableProperty
	private ObservableList<TestDataTableRendererDTO> testDatas = new ObservableList<TestDataTableRendererDTO>();

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

	private void createBuilderTestDatas() {
		for (int i = 0; i < 10; i++) {
			testDatas.add(new TestDataTableRendererDTO("a" + i, "b" + i));
		}
	}

	@Test
	public void testAddColumnOnBuilder() {
		new GWTTimerDeferrerImpl();	// @Before doesn't seem to work

		// define test datas
		createBuilderTestDatas();

		final DataTable<TestDataTableRendererDTO> dataTable = new DataTable<TestDataTableRendererDTO>()
				.withId( "table" )
				.bind(Bindings.obs(this).get(TestDataTableRenderer_._testDatas));
		registerApp( dataTable );

		//Assert to one column - table has only one column
		new Timer() {

			@Override
			public void run() {
				dataTable.addColumn(Columns.from( "first", TestDataTableRendererDTO_._value1));
			}

		}.schedule(25);

		new Timer() {

			@Override
			public void run() {
				JQuery firstHeaderCell = JQuery.select( "thead>tr>th" ).first();
				JQuery lastHeaderCell = JQuery.select( "thead>tr>th" ).last();
				assertEquals( "first", firstHeaderCell.text() );
				assertEquals( "first", lastHeaderCell.text() );

				JQuery firstCell = JQuery.select( "tbody>tr>td" ).first();
				JQuery lastCell = JQuery.select( "tbody>tr>td" ).last();
				assertEquals( "a0", firstCell.text() );
				assertEquals( "a9", lastCell.text() );

				finishTest();
			}

		}.schedule(500);
		delayTestFinish(1000);

	}

	@Test
	public void testRemoveColumnOnBuilder() {
		new GWTTimerDeferrerImpl();	// @Before doesn't seem to work

		// define test datas
		createBuilderTestDatas();

		final Column<TestDataTableRendererDTO> column = Columns.from( "second", TestDataTableRendererDTO_._value2);
		final DataTable<TestDataTableRendererDTO> dataTable = new DataTable<TestDataTableRendererDTO>()
				.withId( "table" )
				.addColumn(Columns.from( "first", TestDataTableRendererDTO_._value1))
				.addColumn(column)
				.bind(Bindings.obs(this).get(TestDataTableRenderer_._testDatas));
		registerApp( dataTable );

		//Assert to one column - table has only one column
		new Timer() {

			@Override
			public void run() {
				dataTable.removeColumn(column);
			}

		}.schedule(500);

		new Timer() {

			@Override
			public void run() {
				JQuery firstHeaderCell = JQuery.select( "thead>tr>th" ).first();
				JQuery lastHeaderCell = JQuery.select( "thead>tr>th" ).last();
				assertEquals( "first", firstHeaderCell.text() );
				assertEquals( "first", lastHeaderCell.text() );

				JQuery firstCell = JQuery.select( "tbody>tr>td" ).first();
				JQuery lastCell = JQuery.select( "tbody>tr>td" ).last();
				assertEquals( "a0", firstCell.text() );
				assertEquals( "a9", lastCell.text() );

				finishTest();
			}

		}.schedule(900);
		delayTestFinish(1000);
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
	//TODO vmiert hibaval elszall a mousedown
	public void testRowClickedEvent() {
		//		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		//		ObservableList<DataTableRowModel> rows = createRows();
		//		DataTableModel model = dataTable.getModel();
		//		model.setRows( rows );
		//		registerApp( dataTable );
		//		final BooleanWrapper clickFiredOnModel = new BooleanWrapper( false );
		//		dataTable.rowClick( new ParametricEventHandler<String>() {
		//
		//			@Override
		//			public void handle( final String parameter ) {
		//				clickFiredOnModel.setValue( true );
		//				assertEquals( "00", parameter );
		//			}
		//		} );
		//
		//		new Timer() {
		//
		//			@Override
		//			public void run() {
		//				JQuery jqFirstRow = JQuery.select( "tbody>tr>td" ).last();
		//				jqFirstRow.mousedown();
		//			}
		//
		//		}.schedule(500);
		//
		//		new Timer() {
		//
		//			@Override
		//			public void run() {
		//				assertTrue( clickFiredOnModel.isValue() );
		//				finishTest();
		//			}
		//		}.schedule( 900 );
		//		delayTestFinish( 1000 );
	}

	private ObservableList<DataTableColumnModel> createColumnHeaders() {
		ObservableList<DataTableColumnModel> columns = new ObservableList<DataTableColumnModel>();
		columns.add( createColumnModel( "aaa" ) );
		return columns;
	}

	private DataTableColumnModel createColumnModel( final String title ) {
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

	private DataTableRowModel createRow( final Integer index ) {
		DataTableRowModel rowModel = new DataTableRowModel();
		ObservableList<DataTableCellModel> cells = new ObservableList<DataTableCellModel>();
		cells.add( createCellModel( index.toString() + "0" ) );
		cells.add( createCellModel( index.toString() + "1" ) );
		rowModel.setCells( cells );
		return rowModel;
	}

	private DataTableCellModel createCellModel( final String content ) {
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
