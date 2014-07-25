package com.doctusoft.dsw.client.comp;

import java.util.List;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel;


public class BarChart extends AbstractChart<BarChart, BarChartModel>{
	
	public BarChart() {
		super( new BarChartModel() );
	}
	
	public BarChart( BarChartModel model ) {
		super( model );
	}
	
	public BarChart withItems(List<BarChartItemModel> items){
		model.setBarChartItems( new  ObservableList<BarChartItemModel>(items ) );
		return this;
	}
	
}
