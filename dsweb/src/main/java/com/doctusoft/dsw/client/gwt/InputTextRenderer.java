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
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;
import com.google.gwt.thirdparty.guava.common.base.Objects;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTextRenderer extends BaseComponentRenderer {

	public InputTextRenderer(final InputTextModel inputText) {
		super(JQuery.select("<input/>"), inputText);
		
		// value
		widget.val(inputText.getValue());
		Bindings.obs(inputText).get(InputTextModel_._value)
		.addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(final String newValue) {
				widget.val(newValue);
			}
		});

		// type
		if (inputText.getInputType()!=null){
			widget.attr("type", String.valueOf(inputText.getInputType()));
		}
		Bindings.obs(inputText).get(InputTextModel_._inputType)
		.addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(final String newValue) {
				widget.attr("type", Objects.firstNonNull(newValue, "text"));
			}
		});
		
		// max length
		if (inputText.getMaxLength() > 0){
			widget.attr("maxlength", String.valueOf(inputText.getMaxLength()));
		}
		Bindings.obs(inputText).get(InputTextModel_._maxLength)
		.addValueChangeListener(new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(final Integer newValue) {
				widget.attr("maxlength", Objects.firstNonNull(String.valueOf(newValue), ""));
			}
		});
		
		// immediate
		// TODO ?
		
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(final JQEvent event, final JQuery currentJQuery) {
				inputText.setValue(widget.val());
			}
		});

		widget.keyup(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (inputText.getImmediate()){
					inputText.setValue(widget.val());
				}
			}
		});
		
		new EnabledAttributeRenderer(widget, inputText);
		new PlaceHolderAttributeRenderer(widget, inputText, InputTextModel_._placeHolder);
	}
	
//	private static String createWidgetText(final String inputType, final String placeHolder) {
//		return "<input type=\"" + inputType + "\"  placeholder=\"" + placeHolder + "\"/>";
//	}
}
