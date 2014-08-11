package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class ChartItemClickedEvent extends AbstractParametricEvent<ChartItemClickParam> implements ModelObject {

	@ObservableProperty
	private ChartItemClickParam eventParameter;

}
