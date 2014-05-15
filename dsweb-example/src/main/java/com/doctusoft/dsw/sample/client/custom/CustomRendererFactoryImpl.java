package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.RendererFactoryImpl;
import com.xedge.jquery.client.JQuery;

public class CustomRendererFactoryImpl extends RendererFactoryImpl {
	
	@Override
	public Renderer<JQuery> resolveRenderer(BaseComponentModel baseWidget) {
		if (baseWidget instanceof CustomComponentModel)
			return new CustomComponentRenderer((CustomComponentModel) baseWidget);
		return super.resolveRenderer(baseWidget);
	}

}
