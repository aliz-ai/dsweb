package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.event.KeyPressedEvent;

public class BaseComponentModel implements ModelObject, HasComponentModel, Serializable {
	
	@ObservableProperty
	private String id = null;
	
	@ObservableProperty
	private Boolean visible = true;
	
	@ObservableProperty
	private ObservableList<String> styleClasses = null;
	
	@ObservableProperty
	private String style = "";
	
	@ObservableProperty
	private int tabIndex = 0;
	
	@ObservableProperty
	private ComponentEvent clicked = null;
	
	@ObservableProperty
	private ComponentEvent focus = null;
	
	@ObservableProperty
	private KeyPressedEvent keyPressed = null;
	
	@Override
	public BaseComponentModel getComponentModel() {
		return this;
	}
	
}
