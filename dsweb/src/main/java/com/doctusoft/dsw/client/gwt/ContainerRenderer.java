package com.doctusoft.dsw.client.gwt;

import java.util.Map;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.BaseComponent;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.Container_;
import com.doctusoft.dsw.client.util.ListBindingListener;
import com.google.common.collect.Maps;
import com.xedge.jquery.client.JQuery;

public class ContainerRenderer extends BaseComponentRenderer {

	private Map<BaseComponent, JQuery> renderedWidgets = Maps.newHashMap();
	
	public ContainerRenderer(Container container) {
		super(JQuery.select("<div/>"), container);
		new ListBindingListener<BaseComponent>((ObservableValueBinding) Bindings.obs(container).get(Container_._children)) {
			@Override
			public void inserted(ObservableList<BaseComponent> list, int index,
					BaseComponent element) {
				widgetAdded(element);
			}
			@Override
			public void removed(ObservableList<BaseComponent> list, int index,
					BaseComponent element) {
				// remove the rendered JQuery element
				renderedWidgets.get(element).remove();
				// remove the handle from the map
				renderedWidgets.remove(element);
			}
		};
	}

	protected void widgetAdded(BaseComponent baseWidget) {
		JQuery rendered = RendererFactory.getRenderer(baseWidget).getWidget();
		widget.append(rendered);
		renderedWidgets.put(baseWidget, rendered);
	}
}
