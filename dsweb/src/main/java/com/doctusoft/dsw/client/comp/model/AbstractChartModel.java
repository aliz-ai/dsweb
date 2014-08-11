
package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;


public class AbstractChartModel extends BaseComponentModel implements ModelObject {
	
	@ObservableProperty
	private LegendPosition legendPosition = LegendPosition.EAST;
	
	@ObservableProperty
	private String title;
	
	@ObservableProperty
	private boolean showTooltip = true;
	
	@ObservableProperty
	private ChartItemClickedEvent rowClickedEvent = new ChartItemClickedEvent();
	
	public static enum LegendPosition {
		NORTH,
		SOUTH,
		WEST,
		EAST;
		
		public String getAbbreviation(){
			return this.name().substring( 0, 1 ).toLowerCase();
		}
	}
}
