package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel_;


public class ContextMenu extends BaseComponent<ContextMenu, ContextMenuModel>{

	public ContextMenu() {
		super(new ContextMenuModel());
	}
	
	public ContextMenu bindObjectId(final ValueBinding<String> objectIdBinding) {
		Bindings.bind(objectIdBinding,Bindings.obs(model).get(ContextMenuModel_._connectedObjectId));
		return this;
	}
	
	public ContextMenu bindSelector(final ValueBinding<String> selectorBinding) {
		Bindings.bind(selectorBinding,Bindings.obs(model).get(ContextMenuModel_._selector));
		return this;
	}
	
	public ContextMenu bindMenuItems(final ObservableValueBinding<? extends ObservableList<String>> listBinding) {
		Bindings.bind((ObservableValueBinding)listBinding,Bindings.obs(model).get(ContextMenuModel_._menuItems));
		return this;
	}

	public ContextMenu addMenuItem(Link link) {
		
		return this;
	}
}
