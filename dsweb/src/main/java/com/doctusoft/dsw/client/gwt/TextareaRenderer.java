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
