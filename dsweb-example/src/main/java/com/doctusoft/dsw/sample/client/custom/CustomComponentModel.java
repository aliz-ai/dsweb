package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class CustomComponentModel extends BaseComponentModel implements ModelObject {

	@ObservableProperty
	private String label;
	
}
