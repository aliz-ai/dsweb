package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.ButtonModel_;
import com.xedge.jquery.client.JQuery;

public class ButtonRenderer extends BaseComponentRenderer {
	
	public ButtonRenderer(final ButtonModel button) {
		super(JQuery.select("<button/>"), button);
		widget.text(button.getCaption());
		widget.addClass("btn");
		Bindings.obs(button).get(ButtonModel_._caption).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text(newValue);
			}
		});
	}

}
