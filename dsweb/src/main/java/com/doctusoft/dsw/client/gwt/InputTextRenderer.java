package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTextRenderer extends BaseComponentRenderer {

	public InputTextRenderer(final InputTextModel inputText) {
		super(JQuery.select("<input type=\""+ inputText.getInputType() + "\" />"), inputText);
		widget.val(inputText.getValue());
		Bindings.obs(inputText).get(InputTextModel_._value).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.val(newValue);
			}
		});
		Bindings.obs(inputText).get(InputTextModel_._inputType).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget = JQuery.select("<input type=\"" + newValue + "\" />");
			}
		});
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				inputText.setValue(widget.val());
			}
		});
	}
}
