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
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel_;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.xedge.jquery.client.JQuery;

public class HistoryHandlerRenderer extends BaseComponentRenderer {
	
	public HistoryHandlerRenderer(final HistoryHandlerModel historyHandler) {
		super(JQuery.select("<span/>"), historyHandler);
		HistoryHandlerModel_._historyToken.addChangeListener(historyHandler, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				History.newItem(newValue);
			}
		});
		// propagate the initial history token to the applicaiton
		historyHandler.setHistoryToken(History.getToken());
		// propagate later user navigations
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				historyHandler.setHistoryToken(event.getValue());
			}
		});
	}
	

}
