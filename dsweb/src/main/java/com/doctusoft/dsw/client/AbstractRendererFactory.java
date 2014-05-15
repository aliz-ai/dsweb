package com.doctusoft.dsw.client;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public abstract class AbstractRendererFactory<ActualBaseComponent> implements RendererFactory<ActualBaseComponent> {

	@Override
	public Renderer<ActualBaseComponent> getRenderer(BaseComponentModel baseWidget) {
		Renderer<ActualBaseComponent> renderer = resolveRenderer(baseWidget);
		if (renderer == null)
			throw new RuntimeException("No renderer for widget: " + baseWidget);
		return renderer;
	}

}
