package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContextMenuModel;

public class ContextMenu<Item extends BaseComponent> extends AbstractContainer<ContextMenu, ContextMenuModel> {
  
  public ContextMenu(String connectedObjectSelector, String contextMenuStyleClass) {
    super(new ContextMenuModel(connectedObjectSelector, contextMenuStyleClass));
    model.setConnectedObjectSelector(connectedObjectSelector);
    model.setContextMenuStyleClass(contextMenuStyleClass);
    model.setElementType("ul id='contextMenu' class='dropdown-menu' role='menu'");
    addStyleClass(contextMenuStyleClass);
    this.setStyle("display:none");
  }
  
  public ContextMenu setConnectedObjectSelector(String conectedObjectSelector) {
    model.setConnectedObjectSelector(conectedObjectSelector);
    return this;
  }
  
  public ContextMenu addMenuItem(Item item) {
    item.appendTo(new BaseContainer("li").appendTo(this));
    return this;
  }
  
  @Override public void addStyleClass(String styleClass) {
    model.setContextMenuStyleClass(styleClass);
    super.addStyleClass(styleClass);
  }
  
}
