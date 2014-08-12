package com.doctusoft.dsw.client.comp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

@NoArgsConstructor
@AllArgsConstructor
public class ContextMenuModel extends ContainerModel implements ModelObject {
  
  @ObservableProperty
  private String connectedObjectSelector = "";
  
  @ObservableProperty
  private String contextMenuStyleClass = "";
  
}
