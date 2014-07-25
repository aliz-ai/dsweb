
package com.doctusoft.dsw.sample.client.person;

import java.util.ArrayList;
import java.util.List;

import com.doctusoft.dsw.client.comp.BarChart;
import com.doctusoft.dsw.client.comp.PieChart;
import com.doctusoft.dsw.client.comp.model.AbstractChartModel.LegendPosition;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ChartView extends ContainerWithPresenter<ChartActivity> {
	
	public ChartView() {
		
		List<PieChartItemModel> items = new ArrayList<PieChartItemModel>();
		items.add( new PieChartItemModel( 12, "Gyula" ) );
		items.add( new PieChartItemModel( 22, "Béla" ) );
		PieChart pieChart = new PieChart()
		.withId( "proba2" )
		.appendTo( container )
		.withItems( items )
		.withLegendPosition( LegendPosition.NORTH )
		.withTitle( "Proba chart" )
		.withShowToolTip( true )
		.rowClick( presenterMethod( ChartActivity_.__handleClick ) );
		
		List<BarChartItemModel> items2 = new ArrayList<BarChartItemModel>();
		
		ArrayList<Integer> list1 = new ArrayList<Integer>(  );
		list1.add( 1 );
		list1.add( 2 );
		list1.add( 3 );
		items2.add(new BarChartItemModel(list1, "tel"));
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(-1 );
		list2.add( 2 );
		list2.add( -3 );
		items2.add(new BarChartItemModel(list2, "nyar"));
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(-1 );
		list3.add( 2 );
		list3.add( -3 );
		items2.add(new BarChartItemModel(list3, "osz"));
		ArrayList<Integer> list4 = new ArrayList<Integer>();
		list4.add(-1 );
		list4.add( 2 );
		list4.add( -20 );
		items2.add(new BarChartItemModel(list4, "tavasz"));
		
		new BarChart()
		.appendTo( container )
		.withItems( items2 )
		.withTitle( "hello" )
		.rowClick( presenterMethod( ChartActivity_.__handleClick ) );
	}
}
