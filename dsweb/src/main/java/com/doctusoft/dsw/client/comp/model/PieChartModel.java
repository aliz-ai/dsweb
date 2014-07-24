
package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

public class PieChartModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private ObservableList<PieChartItemModel> pieChartItems = new ObservableList<PieChartItemModel>();
	
	@com.doctusoft.ObservableProperty
	private LegendPosition legendPosition = LegendPosition.EAST;
	
	@com.doctusoft.ObservableProperty
	private String title;
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return PieChartModel_._observableProperties;
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
