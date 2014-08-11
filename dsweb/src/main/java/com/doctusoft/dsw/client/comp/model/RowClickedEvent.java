package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class RowClickedEvent extends AbstractParametricEvent<Integer> implements ModelObject {

	@ObservableProperty
	private Integer eventParameter;

}
