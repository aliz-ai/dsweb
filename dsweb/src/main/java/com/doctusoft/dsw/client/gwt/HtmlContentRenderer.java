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
import com.doctusoft.dsw.client.comp.model.HtmlContentModel;
import com.doctusoft.dsw.client.comp.model.HtmlContentModel_;
import com.xedge.jquery.client.JQuery;

public class HtmlContentRenderer extends BaseComponentRenderer {
	
	// TODO: we can render the given html without the enclosing "span" element, if the htmlContent
	// contains an enclosing element. Replacing it is a bit tricky, but not impossible
	public HtmlContentRenderer(HtmlContentModel model) {
		super(JQuery.select("<span/>"), model);
		widget.html(model.getHtmlContent());
		addChangeListener(HtmlContentModel_._htmlContent, model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.html(newValue);
			}
		});
	}

}
