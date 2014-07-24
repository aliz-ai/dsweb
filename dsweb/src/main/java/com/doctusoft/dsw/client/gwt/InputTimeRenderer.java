package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.InputTimeModel;
import com.doctusoft.dsw.client.comp.model.InputTimeModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTimeRenderer extends BaseComponentRenderer {

	public InputTimeRenderer(final InputTimeModel model) {
		super(JQuery.select("<input type=\"text\" />"), model);
		
		InputTimeModel_._value.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.val(newValue);
			}
		});
		
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				String checked = checkAndFormatTime(widget.val());
				if (checked == null) {
					widget.focus();
					return;
				}
				if (!widget.val().equals(checked)) {
					widget.val(checked);
				}
				model.setValue(widget.val());				
			}
		});
		
	}
	
	public static String checkAndFormatTime(String time) {
		String newTime = null;
		if (time.matches("([0-9]|0[0-9]|1[0-9]|2[0-3])(|:)[0-5][0-9]")) {
			newTime = time;
			if (time.matches("[0-9](|:)[0-5][0-9]")) {
				newTime = "0" + time;
			}
			if (newTime.matches("[^:]+")) {
				newTime = newTime.substring(0, 2) + ":" + newTime.substring(2, 4); 
			}
		}
		return newTime;
	}

}
