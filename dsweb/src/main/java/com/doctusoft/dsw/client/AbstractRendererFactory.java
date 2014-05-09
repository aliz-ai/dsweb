package com.doctusoft.dsw.client;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.Renderer;

public abstract class AbstractRendererFactory implements RendererFactory {

	@Override
	public Renderer getRenderer(BaseComponentModel baseWidget) {
		Renderer renderer = resolveRenderer(baseWidget);
		if (renderer == null)
			throw new RuntimeException("No renderer for widget: " + baseWidget);
		return renderer;
	}

}
