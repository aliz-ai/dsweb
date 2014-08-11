
package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;

public class PieChartModel extends AbstractChartModel implements ModelObject {
	
	@ObservableProperty
	private ObservableList<PieChartItemModel> pieChartItems = new ObservableList<PieChartItemModel>();
	
}
