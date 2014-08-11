package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class InputTextModel extends BaseComponentModel implements ModelObject {

	@ObservableProperty
	private String value = "";
	
	@ObservableProperty
	private String inputType = "text";


}
