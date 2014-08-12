package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel_;
import com.google.common.base.Strings;

public class ContextMenuRenderer extends ContainerRenderer {
  
  public ContextMenuRenderer(final ContextMenuModel contextMenuModel) {
    super(contextMenuModel);
    
    Bindings.obs(contextMenuModel).get(ContextMenuModel_._connectedObjectSelector)
      .addValueChangeListener(new ValueChangeListener<String>() {
        @Override public void valueChanged(String newValue) {
          // if (!Strings.isNullOrEmpty(contextMenuModel.getContextMenuStyleClass())
          // && !Strings.isNullOrEmpty(newValue)) {
          init(contextMenuModel.getConnectedObjectSelector(), contextMenuModel.getContextMenuStyleClass());
          //}
        }
      });
    
    Bindings.obs(contextMenuModel).get(ContextMenuModel_._contextMenuStyleClass)
      .addValueChangeListener(new ValueChangeListener<String>() {
        @Override public void valueChanged(String newValue) {
          //if (!Strings.isNullOrEmpty(contextMenuModel.getConnectedObjectSelector())
          // && !Strings.isNullOrEmpty(newValue)) {
          init(contextMenuModel.getConnectedObjectSelector(), newValue);
          //}
        }
      });
    
    if (!Strings.isNullOrEmpty(contextMenuModel.getContextMenuStyleClass())
      && !Strings.isNullOrEmpty(contextMenuModel.getContextMenuStyleClass())) {
      init(contextMenuModel.getConnectedObjectSelector(), contextMenuModel.getContextMenuStyleClass());
    }
  }
  
  private native static void init(String connectedObjectSelector, String selector) /*-{
                                                                                   setTimeout(function () { 	
                                                                                   $wnd.$(connectedObjectSelector).contextMenu({
                                                                                   menuSelector: '.' + selector,
                                                                                   menuSelected:	function (invokedOn, selectedMenu) {}
                                                                                   });
                                                                                   }, 10);
                                                                                   }-*/;
}
