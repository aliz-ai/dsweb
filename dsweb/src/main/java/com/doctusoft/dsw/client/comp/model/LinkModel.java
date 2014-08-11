package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class LinkModel extends AbstractContainerModel<BaseComponentModel> implements ModelObject {

	@ObservableProperty
	private String text;

	@ObservableProperty
	private String href;
	
	@ObservableProperty
	private String target;

}
