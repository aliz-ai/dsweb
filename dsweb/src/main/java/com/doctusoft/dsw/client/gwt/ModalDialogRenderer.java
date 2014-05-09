package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel_;
import com.xedge.jquery.client.JQuery;

public class ModalDialogRenderer extends BaseComponentRenderer {

	private JQuery headerText;

	public ModalDialogRenderer(ModalDialogModel modalDialog) {
		super(JQuery.select("<div class=\"modal hide fade\"/>"), modalDialog);
		JQuery header = JQuery.select("<div class=\"modal-header\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\r\n" + 
				"    <h3></h3>\r\n" + 
				"  </div>");
		widget.append(header);
		headerText = header.find("h3");
		headerText.text(modalDialog.getHeading());
		ModalDialogModel_._heading.addChangeListener(modalDialog, new ValueChangeListener<String>() {
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
		if (modalDialog.isDialogVisible()) {
			showDialogNative(widget);
		}
		ModalDialogModel_._dialogVisible.addChangeListener(modalDialog, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue) {
					showDialogNative(widget);
				} else {
					hideDialogNative(widget);
				}
			}
		});
	}
	
	public static native void showDialogNative(JQuery widget) /*-{
		widget.modal('show');
	}-*/;

	public static native void hideDialogNative(JQuery widget) /*-{
		widget.modal('hide');
	}-*/;
}
