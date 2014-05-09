package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Button_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class ButtonRenderer extends BaseComponentRenderer {
	
	public ButtonRenderer(final Button button) {
		super(JQuery.select("<button/>"), button);
		widget.text(button.getCaption());
		widget.addClass("btn");
		Bindings.obs(button).get(Button_._caption).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text(newValue);
			}
		});
		widget.click(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				button.setClicked(true);
				button.setClicked(false);
			}
		});
	}

}
