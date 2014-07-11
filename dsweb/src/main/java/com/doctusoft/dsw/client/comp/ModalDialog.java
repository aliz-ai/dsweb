package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel_;

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
	
	public ModalDialog bindDialogVisible(final ValueBinding<Boolean> visibleBinding) {
		Bindings.bind(visibleBinding, Bindings.obs(model).get(ModalDialogModel_._dialogVisible));
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
