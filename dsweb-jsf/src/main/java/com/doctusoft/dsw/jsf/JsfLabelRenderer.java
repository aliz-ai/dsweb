package com.doctusoft.dsw.jsf;

import javax.faces.component.html.HtmlOutputLabel;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.LabelModel;
import com.doctusoft.dsw.client.comp.model.LabelModel_;

public class JsfLabelRenderer extends BaseJsfRenderer<HtmlOutputLabel> {
	
	public JsfLabelRenderer(LabelModel model) {
		super(new HtmlOutputLabel(), model);
		component.setValue(model.getLabel());
		bind("value", Bindings.obs(model).get(LabelModel_._label));
	}

}
