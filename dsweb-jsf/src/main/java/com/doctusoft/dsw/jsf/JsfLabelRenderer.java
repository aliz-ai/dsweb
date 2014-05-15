package com.doctusoft.dsw.jsf;

import javax.faces.component.html.HtmlOutputLabel;

import com.doctusoft.dsw.client.comp.model.LabelModel;

public class JsfLabelRenderer extends BaseJsfRenderer {
	
	public JsfLabelRenderer(LabelModel model) {
		super(HtmlOutputLabel.class, model);
	}

}
