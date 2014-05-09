package com.doctusoft.dsw.client.gwt;

import lombok.Getter;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.BaseComponent;
import com.doctusoft.dsw.client.comp.BaseComponent_;
import com.doctusoft.dsw.client.util.ListBindingListener;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class BaseComponentRenderer implements Renderer {
	
	@Getter
	protected JQuery widget;

	public BaseComponentRenderer(final JQuery widget, final BaseComponent<?> component) {
		this.widget = widget;
		BaseComponent_._visible.addChangeListener(component, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue) {
					widget.show();
				} else {
					widget.hide();
				}
			}
		});
		new ListBindingListener<String>(Bindings.obs(component).get(BaseComponent_._styleClasses)) {
			@Override
			public void inserted(ObservableList<String> list, int index,
					String element) {
				widget.addClass(element);
			}
			@Override
			public void removed(ObservableList<String> list, int index,
					String element) {
				widget.removeClass(element);
			}
		};
		widget.hover(new EventHandler() {
			
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				component.setHover(true);
				component.setHover(false);
			}
		});
	}

}
