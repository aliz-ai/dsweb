
package com.doctusoft.dsw.client.comp;


import java.util.List;

import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel;

public class PieChart extends AbstractChart<PieChart, PieChartModel> {
	
	private ListBindingListener<PieChartItemModel> itemsListener;
	
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
	
	public PieChart bind(ObservableValueBinding<? extends List<PieChartItemModel>> listBinding ){
		if (itemsListener != null) {
			// remove previous binding
			itemsListener.remove();
		}
		itemsListener = new ListBindingListener<PieChartItemModel>( listBinding ) {
			
			@Override
			public void inserted( ObservableList<PieChartItemModel> list, int index, PieChartItemModel element ) {
				model.getPieChartItems().add( index, element );
			}
			
			@Override
			public void removed( ObservableList<PieChartItemModel> list, int index, PieChartItemModel element ) {
				model.getPieChartItems().remove( index );
			}
		};
		return this;
	}
	
}
