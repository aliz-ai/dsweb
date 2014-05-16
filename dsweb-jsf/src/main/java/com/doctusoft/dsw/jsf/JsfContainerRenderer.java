package com.doctusoft.dsw.jsf;

import javax.faces.component.html.HtmlPanelGroup;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;

public class JsfContainerRenderer extends BaseJsfRenderer<HtmlPanelGroup> {
	
	public JsfContainerRenderer(ContainerModel model) {
		super(new HtmlPanelGroup(), model);
		// in JSF, the view is not modified dynamically, so we don't listen to any events
		// by the time the renderer is attached to the model, the models are already fully built
		// TODO resolve a renderfactory instance in a customizable way
		JsfRendererFactory jsfRendererFactory = new JsfRendererFactory();
		for (BaseComponentModel child : model.getChildren()) {
			 component.getChildren().add(jsfRendererFactory.getRenderer(child).getWidget());
		}
	}

}
