package com.doctusoft.dsw.client.gwt;

import lombok.Getter;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.util.Booleans;
import com.doctusoft.dsw.client.util.ListBindingListener;
import com.xedge.jquery.client.JQuery;

public class BaseComponentRenderer implements Renderer {
	
	@Getter
	protected JQuery widget;

	public BaseComponentRenderer(final JQuery widget, final BaseComponentModel component) {
		this.widget = widget;
		BaseComponentModel_._visible.addChangeListener(component, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Booleans.isTrue(newValue)) {
					widget.show();
				} else {
					widget.hide();
				}
			}
		});
		new ListBindingListener<String>(Bindings.obs(component).get(BaseComponentModel_._styleClasses)) {
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
	}

}
