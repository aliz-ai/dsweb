package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;


public class BarChartModel extends AbstractChartModel{
	
	@com.doctusoft.ObservableProperty
	private ObservableList<BarChartItemModel> barChartItems = new ObservableList<BarChartItemModel>();
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> seriesTitles = new ObservableList<String>();
	
	@com.doctusoft.ObservableProperty
	private BarDirection barDirection = BarDirection.HORIZONTAL;
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return BarChartModel_._observableProperties;
	}
	
	public static enum BarDirection{
		HORIZONTAL, VERTICAL
	}
	
	
}
