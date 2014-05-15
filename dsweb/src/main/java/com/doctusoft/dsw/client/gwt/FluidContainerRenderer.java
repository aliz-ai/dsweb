/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.gwt;

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
import java.util.Map;

/**
 *
 * @author dipacs
 */
public class FluidContainerRenderer extends BaseComponentRenderer {
	
	public static RendererFactory rendererFactory = GWT.create(RendererFactory.class);
	
	private Map<BaseComponentModel, JQuery> renderedWidgets = Maps.newHashMap();
	
	public FluidContainerRenderer(ContainerModel model) {
		super(JQuery.select("<div/>"), model);
        widget.addClass(BootstrapStyleClasses.CONTAINER_FLUID);
		new ListBindingListener<BaseComponentModel>((ObservableValueBinding) Bindings.obs(model).get(AbstractContainerModel_._children)) {
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
