
package com.doctusoft.dsw.client.comp;


import java.util.List;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel;

public class PieChart extends AbstractChart<PieChart, PieChartModel> {
	
	public PieChart() {
		super( new PieChartModel() );
	}
	
	public PieChart( PieChartModel model ) {
		super( model );
	}
	
	public void setItems(List<PieChartItemModel> items){
		model.setPieChartItems( new ObservableList<PieChartItemModel>( items ) );
	}
	
	public PieChart withItems(List<PieChartItemModel> items){
		setItems( items );
		return this;
	}
	
}
