package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class LabelModel extends BaseComponentModel implements ModelObject {

	@ObservableProperty
	private String label = "";
	
	@ObservableProperty
	private String elementName = "span";
	
	public LabelModel() {
	}
	
	public LabelModel(String elementName) {
		this.elementName = elementName;
	}
	
}
