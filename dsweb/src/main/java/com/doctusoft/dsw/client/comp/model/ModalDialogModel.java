package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class ModalDialogModel extends BaseComponentModel implements ModelObject {

	@ObservableProperty
	private String header = "";
	
	@ObservableProperty
	private Boolean dialogVisible;

	@ObservableProperty
	private ContainerModel footerContainer = new ContainerModel();

	@ObservableProperty
	private ContainerModel contentContainer = new ContainerModel();

}
