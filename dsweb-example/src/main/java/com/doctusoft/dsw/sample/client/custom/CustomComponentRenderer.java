package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.gwt.BaseComponentRenderer;
import com.xedge.jquery.client.JQuery;

public class CustomComponentRenderer extends BaseComponentRenderer {
	
	public CustomComponentRenderer(CustomComponentModel model) {
		super(JQuery.select("<h1/>"), model);
		widget.text(model.getLabel());
		CustomComponentModel_._label.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text("updated: " + newValue);
			}
		});
	}

}
