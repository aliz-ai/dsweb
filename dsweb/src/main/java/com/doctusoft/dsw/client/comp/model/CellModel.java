package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class CellModel extends AbstractContainerModel<BaseComponentModel> implements ModelObject {
	
	@ObservableProperty
	private int span = 1;
	
	@ObservableProperty
	private int offset = 0;
	
}
