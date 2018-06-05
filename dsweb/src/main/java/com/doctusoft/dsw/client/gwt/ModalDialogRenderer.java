package com.doctusoft.dsw.client.gwt;

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


import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel_;
import com.google.common.base.Objects;
import com.google.gwt.core.shared.GWT;
import com.xedge.jquery.client.JQuery;

public class ModalDialogRenderer extends BaseComponentRenderer {

	private JQuery headerText;
	private ModalDialogModel modalDialog;
	
	private final RendererFactory<JQuery> rendererFactory = GWT.create( RendererFactory.class );

	public ModalDialogRenderer(ModalDialogModel modalDialog) {
		super(JQuery.select("<div class=\"modal hide fade\"/>"), modalDialog);
		this.modalDialog = modalDialog;
		JQuery header = JQuery.select("<div class=\"modal-header\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\r\n" + 
				"    <h3></h3>\r\n" + 
				"  </div>");
		widget.append(header);
		headerText = header.find("h3");
		addChangeListenerAndApply(ModalDialogModel_._header, modalDialog, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				headerText.text(newValue);
			}
		});
		ContainerRenderer contentRenderer = (ContainerRenderer) rendererFactory.getRenderer(modalDialog.getContentContainer());
		contentRenderer.getWidget().addClass("modal-body");
		widget.append(contentRenderer.getWidget());
		ContainerRenderer footerRenderer = (ContainerRenderer) rendererFactory.getRenderer(modalDialog.getFooterContainer());
		footerRenderer.getWidget().addClass("modal-footer");
		widget.append(footerRenderer.getWidget());
		addChangeListenerAndApply(ModalDialogModel_._dialogVisible, modalDialog, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					showDialogNative(widget);
				} else {
					hideDialogNative(widget);
				}
			}
		});
		install(widget);
	}
	
	protected void dialogClosed() {
		modalDialog.setDialogVisible(false);
	}
	
	@Override
	public void detach() {
		super.detach();
		rendererFactory.dispose(modalDialog.getContentContainer());
		rendererFactory.dispose(modalDialog.getFooterContainer());
	}
	
	@Override
	public void reattach() {
		super.reattach();
		rendererFactory.reattach(modalDialog.getContentContainer());
		rendererFactory.reattach(modalDialog.getFooterContainer());
	}
	
	protected static native void showDialogNative(JQuery widget) /*-{
		widget.modal('show');
	}-*/;

	protected static native void hideDialogNative(JQuery widget) /*-{
		widget.modal('hide');
	}-*/;
	
	protected native void install(JQuery widget) /*-{
		var that = this;
		widget.modal({show: false});
		widget.on('hidden.bs.modal', function(e) {
			that.@com.doctusoft.dsw.client.gwt.ModalDialogRenderer::dialogClosed()();
		});
	}-*/;
}
