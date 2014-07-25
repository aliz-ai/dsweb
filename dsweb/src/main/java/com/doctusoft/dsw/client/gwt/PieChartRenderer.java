
package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.dsw.client.comp.model.ChartItemClickParam;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.xedge.jquery.client.JQuery;

public class PieChartRenderer extends BaseComponentRenderer {
	
	private final PieChartModel model;
	
	public PieChartRenderer( PieChartModel model ) {
		super( JQuery.select( "<div id=\"" + model.getId() + "\"></div>" ), model );
		this.model = model;
		JsArray<JavaScriptObject> jsArray = createJsArrayFromItems( model.getPieChartItems() );
		initPieChartRendererNative(  widget, jsArray, model.getLegendPosition().getAbbreviation(), model.getTitle(), model.isShowTooltip());
	}
	
	private JsArray<JavaScriptObject> createJsArrayFromItems(List<PieChartItemModel> items ){
		JsArray<JavaScriptObject> array = JavaScriptObject.createArray().cast();
		for (PieChartItemModel item : items) {
			JsArray<JavaScriptObject> innerArray = createArrayFromEntry(  item.getName(), item.getValue() );
			array.push( innerArray );
		}
		return array;
	}
	
	private void rowClicked(int itemIndex, int subIndex){
		model.getRowClickedEvent().fire( new ChartItemClickParam( itemIndex, subIndex ) );
	}
	
	private native JsArray<JavaScriptObject> createArrayFromEntry(String name, int value)/*-{
		return [name, value];
	}-*/;
	
	private native void initPieChartRendererNative(JQuery widget, JsArray<JavaScriptObject> data, String legendOrientation, String title, boolean showTooltip) /*-{
		
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
					location : legendOrientation
				},
				highlighter: {
				  show: showTooltip,
				  formatString:'%s',
				  tooltipLocation:'sw',
				  useAxesFormatters:false,
       			  tooltipContentEditor:tooltipContentEditor,
				}
			});
			
		}, 0);
		
		function tooltipContentEditor(str, seriesIndex, pointIndex, plot) {
		    return plot.data[seriesIndex][pointIndex][0]+': '+plot.data[seriesIndex][pointIndex][1];
		}
		
		that = this;
		
		// CLICK CODE START
        widget.bind('jqplotDataClick',
            function (ev, seriesIndex, pointIndex, data) {
           		that.@com.doctusoft.dsw.client.gwt.PieChartRenderer::rowClicked(II)(pointIndex, seriesIndex);
            }
        );
        // CLICK CODE END
	}-*/;
	
	
	
}
