
package com.doctusoft.dsw.client.gwt;

import lombok.Getter;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ParametricEventHandler;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.datatable.AbstractColumn;
import com.doctusoft.dsw.client.comp.datatable.Column;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.datatable.ComponentColumn;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
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
		ObservableList<String> items = new ObservableList<String>(ImmutableList.of("a", "b"));
		dataTable.bind(Bindings.obs(items));
		dataTable.addColumn(new LabelColumn());
		DataTableModel model = dataTable.getModel();
		registerApp( dataTable );
		assertEquals( "a", JQuery.select( "tbody>tr>td:first" ).text() );
		assertEquals( "b", JQuery.select( "tbody>tr>td:last" ).text() );
		assertEquals( 2, JQuery.select("td").length());
		items.remove("b");
		assertEquals( "a", JQuery.select( "tbody>tr>td:last" ).text() );
		assertEquals( 1, JQuery.select("td").length());
		items.add("c");
		assertEquals( "c", JQuery.select( "tbody>tr>td:last" ).text() );
		assertEquals( 2, JQuery.select("td").length());
		model.getRows().get( 1 ).getCells().get( 0 ).setTextContent( "changed" );
		assertEquals( "changed", JQuery.select( "tbody>tr>td:last" ).text() );
	}

	private void createBuilderTestData() {
		for (int i = 0; i < 10; i++) {
			testDatas.add(new TestDataTableRendererDTO("a" + i, "b" + i));
		}
	}

	@Test
	public void testAddColumnOnBuilder() {
		new GWTTimerDeferrerImpl();	// @Before doesn't seem to work

		// define test datas
		createBuilderTestData();

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
		createBuilderTestData();

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
		dataTable.addColumn(new LabelColumn("hello world"));
		registerApp( dataTable );
		assertEquals( "hello world", JQuery.select("thead th").text() );
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
	
	@Test
	public void testHideAndShowColumn() {
		DataTable<String> dataTable = new DataTable<String>();
		dataTable.bind(Bindings.obs(new ObservableList<String>(ImmutableList.of("a", "b", "c"))));
		final LabelColumn firstColumn = new LabelColumn("h1");
		dataTable.addColumn(firstColumn);
		dataTable.addColumn(new LabelColumn("h2"));
		registerApp(dataTable);
		assertEquals("h1h2", JQuery.select("thead").text());
		assertEquals("aabbcc", JQuery.select("tbody").text());
		firstColumn.withVisible(false);
		new Timer() {
			@Override
			public void run() {
				assertEquals("h2", JQuery.select("thead").text());
				assertEquals("abc", JQuery.select("tbody").text());
				firstColumn.withVisible(true);
				new Timer() {
					@Override
					public void run() {
						assertEquals("h1h2", JQuery.select("thead").text());
						assertEquals(1, JQuery.select("tbody").length());
						assertEquals("aabbcc", JQuery.select("tbody").text());
						finishTest();
					}
				}.schedule(25);
			}
		}.schedule(25);
		delayTestFinish(500);
	}
	
	@Test
	public void testTableHeaderComponent() {
		DataTable<String> dataTable = new DataTable<String>();
		dataTable.bind(Bindings.obs(new ObservableList<String>(ImmutableList.of("a", "b", "c"))));
		final LabelColumn firstColumn = new LabelColumn("h1");
		final Button headerButton = new Button("clickme");
		headerButton.click(new EmptyEventHandler() {
			@Override
			public void handle() {
				headerButton.withCaption("clicked");
			}
		});
		firstColumn.getHeader().setHeaderComponent(headerButton.getComponentModel());
		dataTable.addColumn(firstColumn);
		registerApp(dataTable);
		assertEquals("clickme", JQuery.select("button").text());
		JQuery.select("button").click();
		assertEquals("clicked", JQuery.select("button").text());
	}
	
	private static class LabelColumn extends AbstractColumn<LabelColumn, String> {
		@Getter
		private DataTableColumnModel header = new DataTableColumnModel();
		public LabelColumn() {
		}
		public LabelColumn(String title) {
			header.setTitle(title);
		}
		@Override
		public DataTableCellModel getCellModel(String item) {
			DataTableCellModel cellModel = new DataTableCellModel();
			cellModel.setTextContent(item);
			return cellModel;
		}
	}
	
}
