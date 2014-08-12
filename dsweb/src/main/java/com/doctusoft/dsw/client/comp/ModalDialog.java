package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel_;

public class ModalDialog extends BaseComponent<ModalDialog, ModalDialogModel> {
	
	public ModalDialog() {
		super(new ModalDialogModel());
	}
	
	public ModalDialog withHeader(String headerText) {
		model.setHeader(headerText);
		return this;
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
