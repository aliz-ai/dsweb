package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContextMenuModel;


public class ContextMenu<Item extends BaseComponent> extends AbstractContainer<ContextMenu, ContextMenuModel>{

	public ContextMenu() {
		super(new ContextMenuModel());
		model.setElementType("ul id='contextMenu' class='dropdown-menu' role='menu'");
		this.setStyle("display:none");
	}
	
	public ContextMenu setConnectedObjectSelector(String objectClass) {
		this.getModel().setConnectedObjectId(objectClass);
		return this;
	}

	public ContextMenu addMenuItem(Item item) {
		item.appendTo(new BaseContainer("li").appendTo(this));
		return this;
	}
	
	@Override
	public void addStyleClass(String styleClass) {
		this.getModel().setSelector(styleClass);
		super.addStyleClass(styleClass);
	}
	
	@Override
	public ContextMenu appendTo(IsContainer container) {
		return super.appendTo(container);
	}
}
