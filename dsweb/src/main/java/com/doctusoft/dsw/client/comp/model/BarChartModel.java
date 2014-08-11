package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;

public class BarChartModel extends AbstractChartModel implements ModelObject {
	
	@ObservableProperty
	private ObservableList<BarChartItemModel> barChartItems = new ObservableList<BarChartItemModel>();
	
	@ObservableProperty
	private ObservableList<String> seriesTitles = new ObservableList<String>();
	
	@ObservableProperty
	private BarDirection barDirection = BarDirection.HORIZONTAL;
	
	public static enum BarDirection{
		HORIZONTAL, VERTICAL
	}
	
	
}
