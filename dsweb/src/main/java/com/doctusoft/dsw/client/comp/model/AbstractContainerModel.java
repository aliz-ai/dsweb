package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;

public abstract class AbstractContainerModel<Model extends BaseComponentModel> extends BaseComponentModel implements ModelObject {
	
	@ObservableProperty
	private ObservableList<Model> children = new ObservableList<Model>();
	
	@ObservableProperty
	private String elementType = "div";
	
	
}
