package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ModalDialogModel;

public class ModalDialog extends BaseComponent<ModalDialog, ModalDialogModel> {
	
	public ModalDialog() {
		super(new ModalDialogModel());
	}
	
	public ModalDialog addFooter(HasComponentModel component) {
		model.getFooterContainer().getChildren().add(component.getComponentModel());
		return this;
	}

	public ModalDialog addContent(HasComponentModel component) {
		model.getContentContainer().getChildren().add(component.getComponentModel());
		return this;
	}
	
	public ModalDialog show() {
		model.setDialogVisible(true);
		return this;
	}
	
	public ModalDialog hide() {
		model.setDialogVisible(false);
		return this;
	}
}
