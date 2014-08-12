
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

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel.BarDirection;
import com.doctusoft.dsw.client.comp.model.BarChartModel_;
import com.doctusoft.dsw.client.comp.model.ChartItemClickParam;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;
import com.xedge.jquery.client.JQuery;

public class BarChartRenderer extends BaseComponentRenderer {
	
	private final BarChartModel model;
	
	private JQuery lastPlot;
	
	public BarChartRenderer( BarChartModel model ) {
		super( JQuery.select( "<div id=\"" + model.getId() + "\"></div>" ), model );
		this.model = model;
		initializeComponent();
		new ListChangeListener( Bindings.obs( model ).get( BarChartModel_._barChartItems) ) {
			
			@Override
			protected void changed() {
				if (lastPlot != null ) {
					destroyLastPlot( lastPlot );
				}
				initializeComponent();
			}
			
		};
		
	}
	
	private void initializeComponent( ) {
		JsArrayString ticks = JavaScriptObject.createArray().cast();
		ObservableList<BarChartItemModel> barChartItems = model.getBarChartItems();
		for (BarChartItemModel item : barChartItems) {
			ticks.push( item.getName() );
		}
		JsArray<JavaScriptObject> jsValuesArray;
		if (BarDirection.HORIZONTAL.equals( model.getBarDirection())){
			jsValuesArray =  buildValuesArrayHorizontal( barChartItems );
		} else {
			jsValuesArray = buildValuesArray( barChartItems );
		}
		JsArray<JavaScriptObject> series = JavaScriptObject.createArray().cast();
		for (String label : model.getSeriesTitles()) {
			addLabelToArray( series, label );
		}
		initBarChartRendererNative(  widget, ticks, jsValuesArray, series, model.getTitle(), BarDirection.HORIZONTAL.equals( model.getBarDirection() ), model.isShowTooltip(), model.getId());
		
	}
	
	private JsArray<JavaScriptObject> buildValuesArray(List<BarChartItemModel> items){
		JsArray<JavaScriptObject> outerArray = JavaScriptObject.createArray().cast();
		for (BarChartItemModel item : items) {
			List<Integer> itemValues = item.getValues();
			for (int i = 0; i < itemValues.size(); i++) {
				JsArrayInteger innerArray = outerArray.get( i ).cast();
				if (innerArray == null){
					innerArray = JavaScriptObject.createArray().cast();
					outerArray.set( i, innerArray );
				}
				innerArray.push( itemValues.get( i ) );
			}
		}
		return outerArray;
	}
	
	private JsArray<JavaScriptObject> buildValuesArrayHorizontal(List<BarChartItemModel> items){
		JsArray<JavaScriptObject> outerArray = JavaScriptObject.createArray().cast();
		for (int i = 0; i< items.size(); i++) {
			List<Integer> itemValues = items.get( i ).getValues();
			for (int j = 0; j < itemValues.size(); j++) {
				JsArray<JavaScriptObject> innerArray = outerArray.get( j ).cast();
				if (innerArray == null){
					innerArray = JavaScriptObject.createArray().cast();
					outerArray.set( j, innerArray );
				}
				JsArrayInteger mostInnerArray = JavaScriptObject.createArray().cast();
				mostInnerArray.push( itemValues.get( j ) );
				mostInnerArray = pushStrToIntegerArray( mostInnerArray, items.get( i ).getName() );
				innerArray.push( mostInnerArray);
			}
		}
		return outerArray;
	}
	
	private native void addLabelToArray(JsArray<JavaScriptObject> elements, String labelName)/*-{
		var elementToAdd = {
			label : labelName
		}
		elements.push(elementToAdd);
		return
	}-*/;
	
	private native JsArrayInteger pushStrToIntegerArray(JsArrayInteger array, String str)/*-{
		array.push(str);
		return array;
	}-*/;
	
	private void rowClicked(int itemIndex, int subIndex){
		model.getRowClickedEvent().fire( new ChartItemClickParam( itemIndex, subIndex ) );
	}
	
	private void setLastPlot(JQuery plot){
		this.lastPlot = plot;
	}
	
	private native JsArray<JavaScriptObject> createArrayFromEntry(String name, int value)/*-{
		return [name, value];
	}-*/;
	
	private native void destroyLastPlot(JavaScriptObject lastPlot)/*-{
		lastPlot.destroy();
	}-*/;
	
	private native void initBarChartRendererNative(JQuery widget, JsArrayString tickNames, JsArray<JavaScriptObject> values, JsArray<JavaScriptObject> seriesTitles,  String title, boolean isHorizontal, boolean showTooltip, String id) /*-{
		var that = this;
		
		setTimeout(function() {
				// Can specify a custom tick Array.
		    // Ticks should match up one for each y value (category) in the series.
		    var ticks = tickNames;
		    var yAxis = createYAxisObject(isHorizontal);
		    var xAxis = createXAxis(isHorizontal, tickNames);
		    var rendererOptions = buildRendererOptions(isHorizontal);
		     
		    var plot1 = $wnd.$.jqplot(id, values, {
				title : title,
		        // The "seriesDefaults" option is an options object that will
		        // be applied to all series in the chart.
		        seriesDefaults:{
		            renderer:$wnd.$.jqplot.BarRenderer,
		            rendererOptions: rendererOptions
		        },
		        series:seriesTitles,
		        // Show the legend and put it outside the grid, but inside the
		        // plot container, shrinking the grid to accomodate the legend.
		        // A value of "outside" would not shrink the grid and allow
		        // the legend to overflow the container.
		        legend: {
		            show: true,
		            placement: 'outsideGrid'
		        },
		        axes: {
		            xaxis: xAxis,
		            yaxis: yAxis
		        }
		    });
			
			that.@com.doctusoft.dsw.client.gwt.BarChartRenderer::setLastPlot(Lcom/xedge/jquery/client/JQuery;)(plot1);
			
		}, 0);
		
		function buildRendererOptions(isHorizontal){
			var rendererOptions = new Object();
			rendererOptions.barDirection = isHorizontal ? 'horizontal' : 'vertical';
			return rendererOptions;
		}
		
		function createXAxis(isHorizontal, ticks){
			 var xAxis = {};
			 if (!isHorizontal) {
			 	xAxis.ticks = ticks;
			 	xAxis.renderer = $wnd.jQuery.jqplot.CategoryAxisRenderer;
			 }
			 return xAxis;
		}
		
		function createYAxisObject(horizontal){
			var horizontalValue = {
				renderer: $wnd.$.jqplot.CategoryAxisRenderer
			};
			var verticalValue = {};
			if (horizontal){
				return horizontalValue;
			} else {
				return verticalValue;
			}
		}
		
		function tooltipContentEditor(str, seriesIndex, pointIndex, plot) {
		    // display series_label, x-axis_tick, y-axis value
		    return plot.series[seriesIndex]["label"] + ", " + plot.data[seriesIndex][pointIndex];
		}
		
		var that = this;
		
		// CLICK CODE START
		widget.unbind('jqplotDataClick');
        widget.bind('jqplotDataClick',
            function (ev, seriesIndex, pointIndex, data) {
                that.@com.doctusoft.dsw.client.gwt.BarChartRenderer::rowClicked(II)(pointIndex, seriesIndex);
            }
        );
        // CLICK CODE END
}-*/;
}



