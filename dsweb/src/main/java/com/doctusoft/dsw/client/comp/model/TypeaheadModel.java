package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class TypeaheadModel extends SelectModel implements ModelObject {
	
	@ObservableProperty
	private boolean allVisibleOnFocus = false;

}
