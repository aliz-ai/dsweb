package com.doctusoft.dsw.client.gwt;

import com.doctusoft.dsw.client.comp.model.InputTagsModel;

public class InputTagsRenderer extends ContainerRenderer {

	public InputTagsRenderer(InputTagsModel container) {
		super(container);
		widget.find("input").attr("data-role", "tagsinput");
		widget.find("input").attr("placeholder", "Add-tags");
		
		initTagsInput();
		
	}
	
	private native static void initTagsInput() /*-{
		$wnd.$(window).load(function() {
			$wnd.$("input[data-role=tagsinput], select[multiple][data-role=tagsinput]").tagsinput();
		});
	}-*/;
	
}
