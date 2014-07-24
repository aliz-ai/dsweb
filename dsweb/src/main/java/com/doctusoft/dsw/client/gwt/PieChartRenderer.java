
package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.xedge.jquery.client.JQuery;

public class PieChartRenderer extends BaseComponentRenderer {
	
	public PieChartRenderer( PieChartModel model ) {
		super( JQuery.select( "<div id=\"" + model.getId() + "\"></div>" ), model );
		JsArray<JavaScriptObject> jsArray = createJsArrayFromItems( model.getPieChartItems() );
		initPieChartRendererNative(  widget, jsArray, model.getLegendPosition().getAbbreviation(), model.getTitle());
	}
	
	private JsArray<JavaScriptObject> createJsArrayFromItems(List<PieChartItemModel> items ){
		JsArray<JavaScriptObject> array = JavaScriptObject.createArray().cast();
		for (PieChartItemModel item : items) {
			JsArray<JavaScriptObject> innerArray = createArrayFromEntry(  item.getName(), item.getValue() );
			array.push( innerArray );
		}
		return array;
	}
	
	private native JsArray<JavaScriptObject> createArrayFromEntry(String name, int value)/*-{
		return [name, value];
	}-*/;
	
	private native void initPieChartRendererNative(JQuery widget, JsArray<JavaScriptObject> data, String legendOrientation, String title) /*-{
		setTimeout(function() {
			var plot1 = widget.jqplot([ data ], {
				title : title,
				seriesDefaults : {
					shadow : false,
					renderer : $wnd.jQuery.jqplot.PieRenderer,
					rendererOptions : {
						sliceMargin : 4,
						showDataLabels : true
					}
				},
				legend : {
					show : true,
					location : 'w'
				},
				highlighter: {
				  show: true,
				  formatString:'%s', 
				  tooltipLocation:'sw', 
				  useAxesFormatters:false
				}
			});
			
		}, 0);
	}-*/;
	
	
	
}
