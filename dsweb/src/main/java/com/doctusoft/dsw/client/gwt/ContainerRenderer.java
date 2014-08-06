package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel_;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.xedge.jquery.client.JQuery;

public class ContainerRenderer extends BaseComponentRenderer {
	
	public ContainerRenderer(ContainerModel container) {
		super(JQuery.select("<" + container.getElementType() + "/>"), container);
		new ChildrenRenderer(widget, (ObservableValueBinding) Bindings.obs(container).get(AbstractContainerModel_._children));
	}
	
}
