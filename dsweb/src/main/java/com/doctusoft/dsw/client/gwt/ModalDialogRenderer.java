package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

public class ModalDialogRenderer extends BaseComponentRenderer {

	private JQuery headerText;
	private ModalDialogModel modalDialog;

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
		ContainerRenderer contentRenderer = new ContainerRenderer(modalDialog.getContentContainer());
		contentRenderer.getWidget().addClass("modal-body");
		widget.append(contentRenderer.getWidget());
		ContainerRenderer footerRenderer = new ContainerRenderer(modalDialog.getFooterContainer());
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
