package com.doctusoft.dsw.client.gwt;

import com.doctusoft.dsw.client.comp.model.ResourceLoaderModel;
import com.xedge.jquery.client.JQuery;

public class ResourceLoaderRenderer extends BaseComponentRenderer {
	
	public ResourceLoaderRenderer(ResourceLoaderModel model) {
		super(JQuery.select("<span/>"), model);
		switch (model.getResourceType()) {
		case Script:
			AbstractGwtRendererFactory.loadScript(model.getResourcePah());
			break;
		case Stylesheet:
			AbstractGwtRendererFactory.loadStylesheet(model.getResourcePah());
			break;
		}
	}

}
