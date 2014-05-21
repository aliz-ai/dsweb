package com.doctusoft.dsw.client.gwt;

import com.doctusoft.dsw.client.comp.model.DropdownButtonModel;

public class DropdownButtonRenderer extends ContainerRenderer {

	public DropdownButtonRenderer(DropdownButtonModel container) {
		super(container);
		widget.find(".dropdown-toggle").append("<span class=\"caret\"></span>");
		widget.find(".dropdown-toggle").attr("data-toggle", "dropdown"); 
	}
	
}
