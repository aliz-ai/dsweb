
package com.doctusoft.dsw.client.comp;


import java.util.List;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel.LegendPosition;

public class PieChart extends BaseComponent<PieChart, PieChartModel> {
	
	public PieChart() {
		super( new PieChartModel() );
	}
	
	public PieChart( PieChartModel model ) {
		super( model );
	}
	
	public void setItems(List<PieChartItemModel> items){
		model.setPieChartItems( new ObservableList<PieChartItemModel>( items ) );
	}
	
	public PieChart withId( String id ) {
		model.setId( id );
		return this;
	}
	
	public PieChart withItems(List<PieChartItemModel> items){
		setItems( items );
		return this;
	}
	
	public PieChart withLegendPosition(LegendPosition position){
		model.setLegendPosition( position );
		return this;
	}
	
	public PieChart withTitle(String title){
		model.setTitle( title );
		return this;
	}
	
}
