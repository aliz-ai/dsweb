
package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class AbstractChartModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private LegendPosition legendPosition = LegendPosition.EAST;
	
	@com.doctusoft.ObservableProperty
	private String title;
	
	@com.doctusoft.ObservableProperty
	private boolean showTooltip = true;
	
	@com.doctusoft.ObservableProperty
	private ChartItemClickedEvent rowClickedEvent = new ChartItemClickedEvent();
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return AbstractChartModel_._observableProperties;
	}
	
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
