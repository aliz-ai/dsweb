package com.doctusoft.dsw.client.comp;

import java.util.List;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.AbstractChartModel_;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel.BarDirection;
import com.doctusoft.dsw.client.comp.model.BarChartModel_;


public class BarChart extends AbstractChart<BarChart, BarChartModel>{
	
	private ListBindingListener<BarChartItemModel> itemsListener;
	
	public BarChart() {
		super( new BarChartModel() );
	}
	
	public BarChart( BarChartModel model ) {
		super( model );
	}
	
	public BarChart withBarDirection(BarDirection barDirection) {
		model.setBarDirection( barDirection );
		return this;
	}
	
	public BarChart withItems(List<BarChartItemModel> items){
		model.setBarChartItems( new  ObservableList<BarChartItemModel>(items ) );
		return this;
	}
	
	public BarChart withSeriesTitles(List<String> seriesTitles){
		model.setSeriesTitles( new ObservableList<String>( seriesTitles ) );
		return this;
	}
	
	public BarChart bindValues(ObservableValueBinding<? extends List<BarChartItemModel>> listBinding ){
		if (itemsListener != null) {
			// remove previous binding
			itemsListener.remove();
		}
		itemsListener = new ListBindingListener<BarChartItemModel>( listBinding ) {
			
			@Override
			public void inserted( ObservableList<BarChartItemModel> list, int index, BarChartItemModel element ) {
				model.getBarChartItems().add( index, element );
			}
			
			@Override
			public void removed( ObservableList<BarChartItemModel> list, int index, BarChartItemModel element ) {
				model.getBarChartItems().remove( index );
			}
		};
		return this;
	}
	
	public BarChart bindTitle(ValueBinding<String> sourceBinding){
		Bindings.bind( sourceBinding, Bindings.obs( model ).get( AbstractChartModel_._title) );
		return this;
	}
	
	public BarChart bindSeriesTitles(ValueBinding<ObservableList<String>> sourceBinding){
		Bindings.bind( sourceBinding, Bindings.obs( model ).get( BarChartModel_._seriesTitles) );
		return this;
	}
	
}
