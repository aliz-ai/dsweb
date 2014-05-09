package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.HistoryHandler;
import com.doctusoft.dsw.client.comp.HistoryHandler_;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.xedge.jquery.client.JQuery;

public class HistoryHandlerRenderer extends BaseComponentRenderer {
	
	public HistoryHandlerRenderer(final HistoryHandler historyHandler) {
		super(JQuery.select("<poan/>"), historyHandler);
		HistoryHandler_._historyToken.addChangeListener(historyHandler, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				History.newItem(newValue);
			}
		});
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				historyHandler.setHistoryToken(event.getValue());
			}
		});
	}
	

}
