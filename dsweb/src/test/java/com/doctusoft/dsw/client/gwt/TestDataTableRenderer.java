
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ParametricEventHandler;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.datatable.Column;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.datatable.ComponentColumn;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DataTableRowModel;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.client.util.GWTTimerDeferrerImpl;
import com.google.common.collect.ImmutableList;
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
		assertEquals( 4, JQuery.select("td").length());
		model.getRows().remove( 1 );
		lastCell = JQuery.select( "tbody>tr>td" ).last();
		assertEquals( "01", lastCell.text() );
		assertEquals( 2, JQuery.select("td").length());
		model.getRows().add( createRow( 4 ) );
		lastCell = JQuery.select( "tbody>tr>td" ).last();
		assertEquals( "41", lastCell.text() );
		assertEquals( 4, JQuery.select("td").length());
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

				}.schedule(25);
			}

		}.schedule(25);

		delayTestFinish(1000);

	}

	@Test
	public void testRemoveColumnOnBuilder() {
		// define test data
		createBuilderTestDatas();

		final Column<TestDataTableRendererDTO> secondColumn = Columns.from( "second", TestDataTableRendererDTO_._value2);
		final DataTable<TestDataTableRendererDTO> dataTable = new DataTable<TestDataTableRendererDTO>()
				.withId( "table" )
				.addColumn(Columns.from( "first", TestDataTableRendererDTO_._value1))
				.addColumn(secondColumn)
				.bind(Bindings.obs(this).get(TestDataTableRenderer_._testDatas));
		registerApp( dataTable );

		//Assert to one column - table has only one column
		new Timer() {

			@Override
			public void run() {
				dataTable.removeColumn(secondColumn);
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

				}.schedule(25);
			}
		}.schedule(25);

		delayTestFinish(500);
	}

	@Test
	public void testColumnHeaders() {
		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		DataTableModel model = dataTable.getModel();
		ObservableList<DataTableColumnModel> headers = createColumnHeaders();
		model.setColumns( headers );
		model.setRows( createRows() );
		registerApp( dataTable );
		assertEquals( "aaa", JQuery.select("thead th").text() );
	}
	
	/**
	 * this asserts that .detach() is properly invoked on cell renderers
	 */
	@Test
	public void testButtonColumn() {
		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		dataTable.bind(Bindings.obs(new ObservableList<String>(ImmutableList.of("a", "b", "c"))));
		final EmptyEventHandlerMock clickMock = new EmptyEventHandlerMock();
		dataTable.addColumn(new ComponentColumn<String>("button") {
			@Override
			public HasComponentModel getComponent(String item) {
				return new Button("clickme").click(clickMock).withId("btn" + item);
			}
		});
		registerApp(dataTable);
		new Timer() {
			@Override
			public void run() {
				assertEquals(3, JQuery.select("td").length());
				JQuery btn = JQuery.select("#btna");
				assertEquals(1, btn.length());
				btn.click();
				clickMock.assertInvoked();
				finishTest();
			}
		}.schedule(25);
		delayTestFinish(500);
	}

	@Test
	public void testRowClickedEvent() {
		DataTable<String> dataTable = new DataTable<String>().withId( "table" );
		dataTable.bind(Bindings.obs(new ObservableList<String>(ImmutableList.of("a", "b"))));
		dataTable.addColumn(new LabelColumn());
		registerApp( dataTable );
		final EmptyEventHandlerMock clickFiredOnModel = new EmptyEventHandlerMock();
		dataTable.rowClick( new ParametricEventHandler<String>() {

			@Override
			public void handle( final String parameter ) {
				clickFiredOnModel.handle();
				assertEquals( "b", parameter );
			}
		} );

		new Timer() {

			@Override
			public void run() {
				JQuery jqRow = JQuery.select( "tbody>tr" ).last();
				assertEquals(1, jqRow.length());
				jqRow.click();
				new Timer() {
					@Override
					public void run() {
						clickFiredOnModel.assertInvoked();
						finishTest();
					}
				}.schedule( 100 );
			}

		}.schedule(100);

		delayTestFinish( 1000 );
	}

	/**
	 * Tests that if selected items change before the rows are actually attached (due to order of propagation), rendering will still work properly with deferred execution
	 * https://github.com/Doctusoft/dsweb/issues/70
	 */
	@Test
	public void testSelectedIndexAndRowChanges() {
		DataTable<String> dataTable = new DataTable<String>();
		final ObservableList<String> selectionList = new ObservableList<String>(ImmutableList.of("a"));
		dataTable
			.withSelectionMode(SelectionMode.Single)
			.bindSelection(Bindings.obs(selectionList))
			.addColumn(new LabelColumn())
			.bind(Bindings.obs(new ObservableList<String>(ImmutableList.of("a", "b"))));
		registerApp(dataTable);
		new Timer() {
			@Override
			public void run() {
				assertEquals(1, JQuery.select("tbody tr.selected").length());
				assertEquals("a", JQuery.select("tbody tr.selected td").text());
				// update selection
				selectionList.remove("a");
				selectionList.add("b");
				new Timer() {
					@Override
					public void run() {
						assertEquals("b", JQuery.select("tbody tr.selected td").text());
						finishTest();
					}
				}.schedule(50);
			}
		}.schedule(50);
		delayTestFinish(1000);
	}
	
	@Test
	public void testHeadersHiddenAndShown() {
		DataTable<String> dataTable = new DataTable<String>()
				.addColumn(new LabelColumn())
				.withRenderHeaders(false)
				.bind(Bindings.obs(new ObservableList<String>(ImmutableList.of("a", "b", "c"))));
		registerApp(dataTable);
		assertEquals(0, JQuery.select("thead").length());
		dataTable.withRenderHeaders(true);
		assertEquals(1, JQuery.select("thead").length());
		dataTable.withRenderHeaders(false);
		assertEquals(0, JQuery.select("thead").length());
	}
	
	private static class LabelColumn extends ComponentColumn<String> {
		@Override
		public HasComponentModel getComponent(String item) {
			return new Label(item);
		}
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

}
