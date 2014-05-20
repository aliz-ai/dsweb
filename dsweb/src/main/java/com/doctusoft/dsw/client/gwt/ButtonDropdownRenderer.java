package com.doctusoft.dsw.client.gwt;

import com.doctusoft.dsw.client.comp.model.ButtonDropdownModel;
import com.xedge.jquery.client.JQuery;

public class ButtonDropdownRenderer extends ContainerRenderer {

	public ButtonDropdownRenderer(ButtonDropdownModel container) {
		super(container);
		JQuery.select(widget.children().get(0)).append("<span class=\"caret\"></span>");
		dropdown(JQuery.select(widget.children().get(0))); 
	}
	
	private native void dropdown(JQuery toggle) /*-{
		toggle.dropdown();
	}-*/;
	
}
