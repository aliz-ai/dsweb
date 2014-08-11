package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class CheckboxModel extends BaseComponentModel implements ModelObject {
	
	@ObservableProperty
	private String label;
	
	@ObservableProperty
	private Boolean checked = false;
	
}
