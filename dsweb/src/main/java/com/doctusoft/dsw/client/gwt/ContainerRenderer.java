package com.doctusoft.dsw.client.gwt;

import java.util.Map;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel_;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.util.ListBindingListener;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.GWT;
import com.xedge.jquery.client.JQuery;

public class ContainerRenderer extends BaseComponentRenderer {
	
	public static RendererFactory<JQuery> rendererFactory = GWT.create(RendererFactory.class);
	
	private Map<BaseComponentModel, JQuery> renderedWidgets = Maps.newHashMap();
	
	public ContainerRenderer(ContainerModel container) {
		super(JQuery.select("<div/>"), container);
		new ListBindingListener<BaseComponentModel>((ObservableValueBinding) Bindings.obs(container).get(AbstractContainerModel_._children)) {
			@Override
			public void inserted(ObservableList<BaseComponentModel> list, int index,
					BaseComponentModel element) {
				widgetAdded(element);
			}
			
			@Override
			public void removed(ObservableList<BaseComponentModel> list, int index,
					BaseComponentModel element) {
				// remove the rendered JQuery element
				renderedWidgets.get(element).remove();
				// remove the handle from the map
				renderedWidgets.remove(element);
			}
		};
	}
	
	protected void widgetAdded(BaseComponentModel baseWidget) {
		JQuery rendered = rendererFactory.getRenderer(baseWidget).getWidget();
		widget.append(rendered);
		renderedWidgets.put(baseWidget, rendered);
	}
}
