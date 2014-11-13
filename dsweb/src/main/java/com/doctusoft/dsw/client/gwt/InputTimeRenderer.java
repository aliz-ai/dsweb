package com.doctusoft.dsw.client.gwt;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.InputTimeModel;
import com.doctusoft.dsw.client.comp.model.InputTimeModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTimeRenderer extends BaseComponentRenderer {

	public InputTimeRenderer(final InputTimeModel model) {
		super(JQuery.select("<input type=\"text\" placeholder=\"" + model.getPlaceHolder() + "\" />"), model);

		widget.val(model.getValue());
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
					widget.val("");
					widget.focus();
					return;
				}
				if (!widget.val().equals(checked)) {
					widget.val(checked);
				}
				model.setValue(widget.val());
			}
		});

		InputTimeModel_._placeHolder.addChangeListener(model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(String placeHolder) {
				widget.attr("placeholder", placeHolder);
			}
		});
		new EnabledAttributeRenderer(widget, model);
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
