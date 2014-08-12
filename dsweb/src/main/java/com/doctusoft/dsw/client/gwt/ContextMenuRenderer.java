package com.doctusoft.dsw.client.gwt;

import com.doctusoft.dsw.client.comp.model.ContextMenuModel;
import com.google.common.base.Strings;

public class ContextMenuRenderer extends ContainerRenderer {

	public ContextMenuRenderer(final ContextMenuModel contextMenuModel) {
		super(contextMenuModel);

		if (!Strings.isNullOrEmpty(contextMenuModel.getContextMenuStyleClass())
				&& !Strings.isNullOrEmpty(contextMenuModel.getContextMenuStyleClass())) {
			init(contextMenuModel.getConnectedObjectSelector(),contextMenuModel.getContextMenuStyleClass());
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
