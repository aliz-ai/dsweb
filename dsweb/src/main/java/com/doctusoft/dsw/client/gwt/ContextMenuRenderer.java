package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.model.ButtonModel_;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel_;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;
import com.google.common.base.Strings;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class ContextMenuRenderer extends BaseComponentRenderer {

	public ContextMenuRenderer(final ContextMenuModel contextMenuModel) {
		super(JQuery.select("<ul id='contextMenu' class='dropdown-menu' role='menu' style='display:none' />"), contextMenuModel);
		
		new ListBindingListener<String>(Bindings.obs(contextMenuModel).get((ObservableProperty) ContextMenuModel_._menuItems)) {

			@Override
			public void inserted(ObservableList<String> list, int index,
					String element) {
				if (!Strings.isNullOrEmpty(element)) {
					addMenuItems(widget, element);
				}
			}

			@Override
			public void removed(ObservableList<String> list, int index,
					String element) {
				//TODO
			}
		};
		
		Bindings.obs(contextMenuModel).get(ContextMenuModel_._connectedObjectId).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (!Strings.isNullOrEmpty(contextMenuModel.getSelector()) && !Strings.isNullOrEmpty(newValue)) {
					init(contextMenuModel.getConnectedObjectId(), contextMenuModel.getSelector());
				}
			}
		});
		
		Bindings.obs(contextMenuModel).get(ContextMenuModel_._selector).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (!Strings.isNullOrEmpty(contextMenuModel.getConnectedObjectId()) && !Strings.isNullOrEmpty(newValue)) {
					init(contextMenuModel.getConnectedObjectId(), newValue);
				}
			}
		});
		
		if (!Strings.isNullOrEmpty(contextMenuModel.getSelector()) && !Strings.isNullOrEmpty(contextMenuModel.getSelector())) {
			init(contextMenuModel.getConnectedObjectId(),contextMenuModel.getSelector());
		}
	}
	
	private native static void addMenuItems(JQuery element, String menuItem) /*-{
		setTimeout(function () { 
			//$wnd.$('#contextMenu').append('<li><a ><span class="tab">' + menuItem + '</a></span></li>');
			 element.append('<li><a><span class="tab">' + menuItem + '</a></span></li>');
		}, 10);
	}-*/;
	
	private native static void alert(String msg) /*-{
		alert(msg);
	}-*/;
	
	private native static void init(String connectedObject, String selector) /*-{
		alert(selector)
		setTimeout(function () { 	
			$wnd.$('.' + connectedObject).contextMenu({
			    menuSelector: '.' + selector,
			    menuSelected:	function (invokedOn, selectedMenu) {
			        				var msg = 'You selected the menu item ' + selectedMenu.text() +
            									' on the value ' + invokedOn.text() ;
			        				//alert(msg);
			        				@com.doctusoft.dsw.client.gwt.ContextMenuRenderer::alert(Ljava/lang/String;)(msg);
		    					}
			});
		}, 10);
	}-*/;
	
	/*
	private native static void init(String connectedObject, String selector) /*-{
	alert(selector)
	setTimeout(function () { 	
		$wnd.$('.' + connectedObject).contextMenu({
		    menuSelector: '.' + selector,
		    menuSelected:	function (invokedOn, selectedMenu) {
		        				var msg = 'You selected the menu item ' + selectedMenu.text() +
        									' on the value ' + invokedOn.text();
		        				alert(msg);
	    					}
		});
	}, 10);
	}-*///;

}
