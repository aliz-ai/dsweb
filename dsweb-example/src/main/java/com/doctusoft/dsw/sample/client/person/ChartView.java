
package com.doctusoft.dsw.sample.client.person;

import java.util.ArrayList;

import com.doctusoft.dsw.client.comp.PieChart;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel.LegendPosition;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ChartView extends ContainerWithPresenter<ChartActivity> {
	
	public ChartView() {
		
		ArrayList<PieChartItemModel> items = new ArrayList<PieChartItemModel>();
		items.add( new PieChartItemModel( 12, "Gyula" ) );
		items.add( new PieChartItemModel( 22, "Béla" ) );
		PieChart pieChart = new PieChart()
			.withId( "proba2" )
			.appendTo( container )
			.withItems( items )
			.withLegendPosition( LegendPosition.NORTH )
			.withTitle( "Proba chart" );
	}
}
