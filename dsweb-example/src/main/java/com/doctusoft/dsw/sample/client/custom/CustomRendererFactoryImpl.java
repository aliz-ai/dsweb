package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.Renderer;
import com.doctusoft.dsw.client.gwt.RendererFactoryImpl;

public class CustomRendererFactoryImpl extends RendererFactoryImpl {
	
	@Override
	public Renderer resolveRenderer(BaseComponentModel baseWidget) {
		if (baseWidget instanceof CustomComponentModel)
			return new CustomComponentRenderer((CustomComponentModel) baseWidget);
		return super.resolveRenderer(baseWidget);
	}

}
