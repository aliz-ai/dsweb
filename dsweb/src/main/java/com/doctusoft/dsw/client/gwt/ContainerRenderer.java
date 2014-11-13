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


import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel_;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.xedge.jquery.client.JQuery;

public class ContainerRenderer extends BaseComponentRenderer {
	
	private ChildrenRenderer childrenRenderer;

	public ContainerRenderer(ContainerModel container) {
		super(JQuery.select("<" + container.getElementType() + "/>"), container);
		childrenRenderer = new ChildrenRenderer(widget, (ObservableValueBinding) Bindings.obs(container).get(AbstractContainerModel_._children));
	}
	
	@Override
	public void detach() {
		childrenRenderer.detach();
	}
	
	@Override
	public void reattach() {
		childrenRenderer.reattach();
	}
}
