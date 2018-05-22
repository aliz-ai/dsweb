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
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel_;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.LinkModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

public class LinkRenderer extends BaseComponentRenderer {
	
	public LinkRenderer(final LinkModel link) {
		super(JQuery.select("<a/>"), link);
		new ChildrenRenderer(widget, (ObservableValueBinding) Bindings.obs(link).get(AbstractContainerModel_._children));
		widget.attr("target", link.getTarget());
		addChangeListenerAndApply(LinkModel_._text, link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (newValue != null) {
					widget.text(newValue);
				}
			}
		});
		addChangeListenerAndApply(LinkModel_._href, link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.attr("href", newValue);
			}
		});
		if (link.getClicked() != null && link.getClicked().isHasListeners()) {
			clearHref();
		} else {
			Bindings.obs(link).get(BaseComponentModel_._clicked).get(ComponentEvent_._hasListeners).addValueChangeListener(new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(Boolean newValue) {
					if (Objects.firstNonNull(newValue, false)) {
						clearHref();
					}
				}
			});
		}
		new DisabledStyleClassRenderer(widget, link, this);
	}
	
	protected void clearHref() {
		widget.attr("href","javascript:;");
	}

}
