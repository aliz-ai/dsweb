package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.xedge.jquery.client.JQuery;

public class MaxLengthRenderer {
	
	public <Model extends BaseComponentModel> MaxLengthRenderer(final JQuery input, Model model, ObservableProperty<Model, Integer> property) {
		BaseComponentRenderer.addChangeListenerAndApply(property, model, new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(final Integer newValue) {
				if (newValue != null) {
					input.attr("maxlength", String.valueOf(newValue));
				} else {
					input.removeAttr("maxlength");
				}
			}
		});
		
	}

}
