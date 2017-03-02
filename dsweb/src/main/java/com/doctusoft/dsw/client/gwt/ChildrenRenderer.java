
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.util.List;
import java.util.Map;

import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.GWT;
import com.xedge.jquery.client.JQuery;

public class ChildrenRenderer {
	
	public static RendererFactory<JQuery> rendererFactory = GWT.create( RendererFactory.class );
	
	private Map<BaseComponentModel, Renderer<JQuery>> renderedWidgets = Maps.newHashMap();
	
	private JQuery widget;
	
	public ChildrenRenderer( JQuery widget, ObservableValueBinding<? extends List<BaseComponentModel>> childrenBinding ) {
		this.widget = widget;
		new ListBindingListener<BaseComponentModel>( childrenBinding ) {
			
			@Override
			public void inserted( ObservableList<BaseComponentModel> list, int index,
				BaseComponentModel element ) {
				widgetAdded( element, index );
			}
			
			@Override
			public void removed( ObservableList<BaseComponentModel> list, int index,
				BaseComponentModel element ) {
				rendererFactory.dispose( element );
				// remove the handle from the map
				renderedWidgets.remove( element ).getWidget().get(0).removeFromParent();
			}
		};
	}
	
	protected void widgetAdded( BaseComponentModel baseWidget, int index ) {
		Renderer<JQuery> renderer = rendererFactory.getRenderer( baseWidget );
		JQuery rendered = renderer.getWidget();
		JQuery children = widget.children();
		int addedWidgetsCount = children.length();
		if (addedWidgetsCount == 0 || addedWidgetsCount == index) {
			widget.append( rendered );
		}
		else {
			rendered.insertBefore( children.get( index ) );
		}
		renderedWidgets.put( baseWidget, renderer );
	}
	
	public void detach() {
		for (BaseComponentModel child : renderedWidgets.keySet()) {
			rendererFactory.dispose(child);
		}
	}
	
	public void reattach() {
		for (BaseComponentModel child : renderedWidgets.keySet()) {
			rendererFactory.reattach(child);
		}
	}
}
