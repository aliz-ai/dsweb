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
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.ButtonModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

public class ButtonRenderer extends BaseComponentRenderer {
	
	public ButtonRenderer(final ButtonModel button) {
		super(JQuery.select("<button/>"), button);
		widget.addClass("btn");
		widget.attr("type", "button");		// this prevents submitting when on a form
		addChangeListenerAndApply(ButtonModel_._caption, button, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text(Objects.firstNonNull(newValue, ""));
			}
		});
		addChangeListenerAndApply(ButtonModel_._iconClassName, button, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (newValue == null) {
					widget.find("i").remove();
				} else {
					JQuery icon = widget.find("i");
					if (icon.length() == 0) {
						icon = JQuery.select("<i/>").prependTo(widget);
					}
					icon.attr("class", newValue);
					// ensure that the button text begins with a space
					// using jquery text() would override the inserted icon, so we have to 'hack' on the text node 
					widget.contents().get(1).setNodeValue(" " + Objects.firstNonNull(button.getCaption(), ""));
				}
			}
		});
	}
	
}
