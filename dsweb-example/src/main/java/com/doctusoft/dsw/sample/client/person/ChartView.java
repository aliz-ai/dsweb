
package com.doctusoft.dsw.sample.client.person;

/*
 * #%L
 * dsweb-example
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


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.BarChart;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.PieChart;
import com.doctusoft.dsw.client.comp.model.AbstractChartModel.LegendPosition;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel.BarDirection;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity_;

public class ChartView extends ContainerWithPresenter<ShowcaseActivity> {
	
	@Getter
	private final BarChart barChart;
	
	@Getter
	private final PieChart pieChart;
	
	public ChartView() {
		
		List<PieChartItemModel> items = new ArrayList<PieChartItemModel>();
		items.add( new PieChartItemModel( 12, "Gyula" ) );
		items.add( new PieChartItemModel( 22, "Béla" ) );
		pieChart = new PieChart()
		.withId( "proba2" )
		.appendTo( container )
		.withItems( items )
		.withLegendPosition( LegendPosition.EAST )
		.withTitle( "Proba chart" )
		.withShowToolTip( true )
		.rowClick( presenterMethod( ShowcaseActivity_.__chartClicked ) );
		
		List<BarChartItemModel> items2 = new ArrayList<BarChartItemModel>();
		
		items2.add( createDummyBarChartItemModel( 1, 2, 3, "tel" ));
		items2.add( createDummyBarChartItemModel( 1, -2, 8, "nyar" ));
		items2.add( createDummyBarChartItemModel( 41, 2, 3, "osz" ));
		items2.add( createDummyBarChartItemModel( -1, 12, 3, "tavasz" ));
		
		ArrayList<String> seriesTitles = new ArrayList<String>();
		seriesTitles.add( "egy" );
		seriesTitles.add( "ket" );
		seriesTitles.add( "ha" );
		barChart = new BarChart()
		.appendTo( container )
		.withItems( items2 )
		.withTitle( "hello" )
		.rowClick( presenterMethod( ShowcaseActivity_.__chartClicked ) )
		.withBarDirection( BarDirection.HORIZONTAL)
		.withSeriesTitles( seriesTitles )
		.withId( "hello" );
		new Button("Add new chart value").appendTo(container).click( new EmptyEventHandler() {
			
			@Override
			public void handle() {
				barChart.getModel().getBarChartItems().add( createDummyBarChartItemModel( 12, 20, 1, "aaa" ) );
				pieChart.getModel().getPieChartItems().add( new PieChartItemModel( 20, "AAAAAAAA" ) );
			}
		} );
	}
	
	private BarChartItemModel createDummyBarChartItemModel(int v1, int v2, int v3, String nev){
		ArrayList<Integer> list1 = new ArrayList<Integer>(  );
		list1.add( v1 );
		list1.add( v2 );
		list1.add( v3 );
		return new BarChartItemModel(list1, nev);
	}
	
}
