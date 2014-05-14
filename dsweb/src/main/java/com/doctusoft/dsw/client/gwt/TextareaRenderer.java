package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.doctusoft.dsw.client.comp.model.TextareaModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TextareaRenderer extends BaseComponentRenderer {

	public TextareaRenderer(final TextareaModel textarea) {
		super(JQuery.select("<textarea rows=\"" + textarea.getRows() +"\"></textarea>"), textarea);
		
		widget.val(textarea.getValue());
		Bindings.obs(textarea).get(TextareaModel_._value).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.val(newValue);
			}
		});
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				textarea.setValue(widget.val());
			}
		});
	}

}
