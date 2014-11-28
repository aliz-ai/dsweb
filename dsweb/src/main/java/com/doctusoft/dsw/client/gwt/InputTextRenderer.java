package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;
import com.doctusoft.html4j.jquery.EventHandler;
import com.doctusoft.html4j.jquery.JQEvent;
import com.doctusoft.html4j.jquery.JQuery;

public class InputTextRenderer extends BaseComponentRenderer {

	public InputTextRenderer(final InputTextModel inputText) {
		super(JQuery.select(createWidgetText(inputText.getInputType(), inputText.getPlaceHolder())), inputText);
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
				widget = JQuery.select(createWidgetText(newValue, inputText.getPlaceHolder()));
			}
		});

		Bindings.obs(inputText).get(InputTextModel_._placeHolder).addValueChangeListener(new ValueChangeListener<String>() {

			@Override
			public void valueChanged(String placeHolder) {
				widget.attr("placeHolder", placeHolder);

			}
		});

		Bindings.obs(inputText).get(InputTextModel_._inputType)
		.addValueChangeListener(new ValueChangeListener<String>() {
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
		new EnabledAttributeRenderer(widget, inputText);
	}

	private static String createWidgetText(String inputType, String placeHolder) {
		return "<input type=\"" + inputType + "\"  placeholder=\""
				+ placeHolder + "\"/>";
	}
}
