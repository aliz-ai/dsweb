package com.doctusoft.dsw.jsf;

import javax.faces.component.UIComponent;

import com.doctusoft.dsw.client.AbstractRendererFactory;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.comp.model.LabelModel;

public class JsfRendererFactory extends AbstractRendererFactory<UIComponent> {
	
	@Override
	public Renderer<UIComponent> resolveRenderer(BaseComponentModel model) {
		if (model instanceof LabelModel)
			return new JsfLabelRenderer((LabelModel) model);
		if (model instanceof ContainerModel)
			return new JsfContainerRenderer((ContainerModel) model);
		return null;
	}

}
