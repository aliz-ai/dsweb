package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.event.KeyPressedEvent;

public class BaseComponentModel implements ModelObject, HasComponentModel, Serializable {
	
	@com.doctusoft.ObservableProperty
	private String id = null;
	
	@com.doctusoft.ObservableProperty
	private Boolean visible = true;
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> styleClasses = null;
	
	@com.doctusoft.ObservableProperty
	private String style = "";
	
	@com.doctusoft.ObservableProperty
	private int tabIndex = 0;
	
	@com.doctusoft.ObservableProperty
	private ComponentEvent clicked = null;
	
	@com.doctusoft.ObservableProperty
	private ComponentEvent focus = null;
	
	@com.doctusoft.ObservableProperty
	private KeyPressedEvent keyPressed = null;
	
	@Override
	public BaseComponentModel getComponentModel() {
		return this;
	}
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return BaseComponentModel_._observableProperties;
	}
	
}
