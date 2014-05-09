package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Label_;
import com.xedge.jquery.client.JQuery;

public class LabelRenderer extends BaseComponentRenderer {
	
	public LabelRenderer(Label label) {
		super(JQuery.select("<span/>"), label);
		widget.text(label.getLabel());
		Bindings.obs(label).get(Label_._label).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text(newValue);
			}
		});
	}

}
