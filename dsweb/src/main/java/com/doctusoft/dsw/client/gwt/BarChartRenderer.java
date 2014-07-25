
package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel;
import com.doctusoft.dsw.client.comp.model.ChartItemClickParam;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;
import com.xedge.jquery.client.JQuery;

public class BarChartRenderer extends BaseComponentRenderer {
	
	private final BarChartModel model;
	
	public BarChartRenderer( BarChartModel model ) {
		super( JQuery.select( "<div id=\"" + model.getId() + "\"></div>" ), model );
		this.model = model;
		JsArrayString ticks = JavaScriptObject.createArray().cast();
		ObservableList<BarChartItemModel> barChartItems = model.getBarChartItems();
		for (BarChartItemModel item : barChartItems) {
			ticks.push( item.getName() );
		}
		JsArray<JavaScriptObject> jsValuesArray = buildValuesArray( barChartItems );
		initBarChartRendererNative(  widget, ticks, jsValuesArray, model.getTitle(), model.isShowTooltip());
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
	
	private native JsArrayInteger pushStrToIntegerArray(JsArrayInteger array, String str)/*-{
		array.push(str);
		return array;
	}-*/;
	
	private void rowClicked(int itemIndex, int subIndex){
		model.getRowClickedEvent().fire( new ChartItemClickParam( itemIndex, subIndex ) );
	}
	
	private native JsArray<JavaScriptObject> createArrayFromEntry(String name, int value)/*-{
		return [name, value];
	}-*/;
	
	private native void initBarChartRendererNative(JQuery widget, JsArrayString tickNames, JsArray<JavaScriptObject> values, String title, boolean showTooltip) /*-{
		var that = this;
		setTimeout(function() {
				// Can specify a custom tick Array.
		    // Ticks should match up one for each y value (category) in the series.
		    var ticks = tickNames;
		     
		    var plot1 = widget.jqplot(values, {
				title : title,
		        // The "seriesDefaults" option is an options object that will
		        // be applied to all series in the chart.
		        seriesDefaults:{
		            renderer:$wnd.$.jqplot.BarRenderer,
		            rendererOptions: {
		            	fillToZero: true,
		            }
		        },
		        // Custom labels for the series are specified with the "label"
		        // option on the series option.  Here a series option object
		        // is specified for each series.
		        series:[
		            {label:'Hotel'},
		            {label:'Event Regristration'},
		            {label:'Airfare'}
		        ],
		        // Show the legend and put it outside the grid, but inside the
		        // plot container, shrinking the grid to accomodate the legend.
		        // A value of "outside" would not shrink the grid and allow
		        // the legend to overflow the container.
		        legend: {
		            show: true,
		            placement: 'outsideGrid'
		        },
		        highlighter: {
					show:showTooltip,
        			tooltipContentEditor:tooltipContentEditor,
        			showMarker:false
				},
		        axes: {
		            // Use a category axis on the x axis and use our custom ticks.
		            xaxis: {
		                renderer: $wnd.$.jqplot.CategoryAxisRenderer,
		                ticks: ticks
		            },
		            // Pad the y axis just a little so bars can get close to, but
		            // not touch, the grid boundaries.  1.2 is the default padding.
		            yaxis: {
		                pad: 1.05,
		            }
		        }
		    });
		}, 0);
		
		function tooltipContentEditor(str, seriesIndex, pointIndex, plot) {
		    // display series_label, x-axis_tick, y-axis value
		    return plot.series[seriesIndex]["label"] + ", " + plot.data[seriesIndex][pointIndex];
		}
		
		var that = this;
		
		// CLICK CODE START
        widget.bind('jqplotDataClick',
            function (ev, seriesIndex, pointIndex, data) {
                that.@com.doctusoft.dsw.client.gwt.BarChartRenderer::rowClicked(II)(pointIndex, seriesIndex);
            }
        );
        // CLICK CODE END
}-*/;
}



