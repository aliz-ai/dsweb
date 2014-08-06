package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class Composite<Root extends BaseComponent> implements HasComponentModel {
	
	protected Root root;
	
	public Composite(Root root) {
		this.root = root;
	}
	
	public Composite<Root> appendTo(IsContainer container) {
		container.add(this);
		return this;
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return root.getComponentModel();
	}

}
