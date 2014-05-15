package com.doctusoft.dsw.client;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public interface RendererFactory<ActualBaseComponent> {

	/**
	 * 
	 * @return null if no renderer was ounf
	 */
	public Renderer<ActualBaseComponent> resolveRenderer(BaseComponentModel baseWidget);

	/**
	 * 
	 * @throws RuntimeException if no renderer was found
	 */
	public Renderer<ActualBaseComponent> getRenderer(BaseComponentModel baseWidget);
}
