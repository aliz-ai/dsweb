package com.doctusoft.dsw.client.gwt;

import java.math.BigDecimal;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.InputNumberModel;
import com.doctusoft.dsw.client.comp.model.InputNumberModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputNumberRenderer extends BaseComponentRenderer {

	public InputNumberRenderer(final InputNumberModel model) {
		super(JQuery.select("<input type=\"number\" placeholder=\"" + model.getPlaceHolder() + "\"/>"), model);
		BigDecimal value = model.getValue();
		if (value != null) {
			widget.val(value.toPlainString());
		}
		InputNumberModel_._value.addChangeListener(model, new ValueChangeListener<BigDecimal>() {
			@Override
			public void valueChanged(final BigDecimal newValue) {
				if (newValue == null) {
					widget.val("");
				} else {
					widget.val(newValue.toPlainString());
				}
			}
		});
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(final JQEvent event, final JQuery currentJQuery) {
				try {
					BigDecimal value = new BigDecimal(widget.val());
					model.setValue(value);
				} catch (NumberFormatException e) {
					// TODO conversion error handling?
					model.setValue(null);
				}
			}
		});

		new EnabledAttributeRenderer(widget, model);
		new PlaceHolderAttributeRenderer(widget, model, InputNumberModel_._placeHolder);
	}

}
