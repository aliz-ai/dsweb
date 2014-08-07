package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel_;
import com.google.common.base.Strings;

public class ContextMenuRenderer extends ContainerRenderer {

	public ContextMenuRenderer(final ContextMenuModel contextMenuModel) {
		super(contextMenuModel);
		
		Bindings.obs(contextMenuModel).get(ContextMenuModel_._connectedObjectId).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (!Strings.isNullOrEmpty(contextMenuModel.getSelector()) && !Strings.isNullOrEmpty(newValue)) {
					init(contextMenuModel.getConnectedObjectId(), contextMenuModel.getSelector());
				}
			}
		});
		
		Bindings.obs(contextMenuModel).get(ContextMenuModel_._selector).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (!Strings.isNullOrEmpty(contextMenuModel.getConnectedObjectId()) && !Strings.isNullOrEmpty(newValue)) {
					init(contextMenuModel.getConnectedObjectId(), newValue);
				}
			}
		});
		
		if (!Strings.isNullOrEmpty(contextMenuModel.getSelector()) && !Strings.isNullOrEmpty(contextMenuModel.getSelector())) {
			init(contextMenuModel.getConnectedObjectId(),contextMenuModel.getSelector());
		}
	}
	
	private native static void init(String connectedObjectSelector, String selector) /*-{
		setTimeout(function () { 	
			$wnd.$(connectedObjectSelector).contextMenu({
			    menuSelector: '.' + selector,
			    menuSelected:	function (invokedOn, selectedMenu) {}
			});
		}, 10);
	}-*/;
}
