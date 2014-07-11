package com.doctusoft.dsw.client.gwt;

import lombok.Getter;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.doctusoft.dsw.client.util.Booleans;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.JQuery.EventType;
import com.xedge.jquery.client.handlers.EventHandler;

public class BaseComponentRenderer implements Renderer<JQuery> {
	
	protected static <T, K> void addChangeListener(ObservableProperty<K, T> property, BaseComponentModel model, ValueChangeListener<T> listener) {
		property.addChangeListener((K) model, listener);
		listener.valueChanged(property.getValue((K) model));
	}
	
	@Getter
	protected JQuery widget;
	
	public BaseComponentRenderer(final JQuery widget, final BaseComponentModel component) {
		this.widget = widget;
		
		addChangeListener(BaseComponentModel_._visible, component, new ValueChangeListener<Boolean>() {
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
		applyStyle(component.getStyle());
		addChangeListener(BaseComponentModel_._style, component, new ValueChangeListener<String>() {
			
			@Override
			public void valueChanged(String newValue) {
				applyStyle(newValue);
			}
		});
		bindEvent(EventType.click, Bindings.obs(component).get(BaseComponentModel_._clicked));
	}
	
	protected void applyStyle(String style) {
		widget.attr("style", Strings.isNullOrEmpty(style)?null:style);
	}
	
	protected void bindEvent(final EventType eventType, final ObservableChainedValueBindingBuilder<ComponentEvent> eventBinding) {
		// if we already know that this event has handlers in the UI code
		if (eventBinding.getValue().isHasListeners()) {
			doBindEventInner(eventType, eventBinding);
		} else {
			// if not yet, we add a listener por si acaso later one will be attached
			eventBinding.get(ComponentEvent_._hasListeners).addValueChangeListener(new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(Boolean newValue) {
					if (Objects.firstNonNull(newValue, false)) {
						doBindEventInner(eventType, eventBinding);
					}
				}
			});
		}
	}
	
	protected void doBindEventInner(EventType eventType, final ObservableValueBinding<ComponentEvent> eventBinding) {
		widget.bind(eventType, new EventHandler() {
			@Override
			public void eventComplete(JQEvent jqEvent, JQuery currentJQuery) {
				eventBinding.getValue().fire();
			}
		});
	}
	
}
