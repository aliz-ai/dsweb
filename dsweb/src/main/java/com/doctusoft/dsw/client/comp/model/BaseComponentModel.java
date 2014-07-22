package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.HasComponentModel;

public class BaseComponentModel implements ModelObject, HasComponentModel, Serializable {
	
	@com.doctusoft.ObservableProperty
	private String id = null;
	
	@com.doctusoft.ObservableProperty
	private Boolean visible = true;
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> styleClasses = new ObservableList<String>();
	
	@com.doctusoft.ObservableProperty
	private String style = "";
	
	@com.doctusoft.ObservableProperty
	private int tabIndex = 0;
	
	@com.doctusoft.ObservableProperty
	private ComponentEvent clicked = new ComponentEvent();
	
	@com.doctusoft.ObservableProperty
	private ComponentEvent focus = new ComponentEvent();
	
	@Override
	public BaseComponentModel getComponentModel() {
		return this;
	}
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return BaseComponentModel_._observableProperties;
	}
	
}
