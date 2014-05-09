package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.LabelModel;
import com.doctusoft.dsw.client.comp.model.LabelModel_;
import com.xedge.jquery.client.JQuery;

public class LabelRenderer extends BaseComponentRenderer {
	
	public LabelRenderer(LabelModel label) {
		super(JQuery.select("<span/>"), label);
		widget.text(label.getLabel());
		Bindings.obs(label).get(LabelModel_._label).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text(newValue);
			}
		});
	}

}
