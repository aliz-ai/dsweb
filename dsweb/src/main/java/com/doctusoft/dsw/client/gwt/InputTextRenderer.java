package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.InputText_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTextRenderer extends BaseComponentRenderer {

	public InputTextRenderer(final InputText inputText) {
		super(JQuery.select("<input type=\"text\"/>"), inputText);
		widget.val(inputText.getValue());
		Bindings.obs(inputText).get(InputText_._value).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.val(newValue);
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
