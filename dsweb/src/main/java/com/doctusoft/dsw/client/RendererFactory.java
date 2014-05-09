package com.doctusoft.dsw.client;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.Renderer;

public interface RendererFactory {

	/**
	 * 
	 * @return null if no renderer was ounf
	 */
	public Renderer resolveRenderer(BaseComponentModel baseWidget);

	/**
	 * 
	 * @throws RuntimeException if no renderer was found
	 */
	public Renderer getRenderer(BaseComponentModel baseWidget);
}
