package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class ModalDialogModel extends BaseComponentModel {

	@com.doctusoft.ObservableProperty
	private String heading = "";

	@com.doctusoft.ObservableProperty
	private boolean dialogVisible = false;

	@com.doctusoft.ObservableProperty
	private ContainerModel footerContainer = new ContainerModel();

	@com.doctusoft.ObservableProperty
	private ContainerModel contentContainer = new ContainerModel();

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return ModalDialogModel_._observableProperties;
	}
}
