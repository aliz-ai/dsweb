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
import com.doctusoft.dsw.client.comp.model.event.KeyPressedEvent;
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
		applyTabIndex(component.getTabIndex());
		BaseComponentModel_._tabIndex.addChangeListener(component, new ValueChangeListener<Integer>() {

			@Override
			public void valueChanged(Integer newValue) {
				applyTabIndex(newValue);
			}
		});
		
		bindEvent(EventType.click, Bindings.obs(component).get(BaseComponentModel_._clicked));
		bindEvent(EventType.keypress, Bindings.obs(component).get(BaseComponentModel_._keyPressed), new EventTriggerer<KeyPressedEvent>() {
			@Override
			public void triggerEvent(KeyPressedEvent event, JQEvent jqEvent) {
				event.fire(jqEvent.which());
			}
		});
		
		bindFocus(component);
	}
	
	protected void applyStyle(String style) {
		widget.attr("style", Strings.isNullOrEmpty(style)?null:style);
	}
	
	protected void applyTabIndex(int tabIndex) {
		widget.attr("tabindex", tabIndex == 0 ? null : String.valueOf(tabIndex));
	}
	
	protected void bindEvent(final EventType eventType, final ObservableChainedValueBindingBuilder<ComponentEvent> eventBinding) {
		bindEvent(eventType, eventBinding, new EventTriggerer<ComponentEvent>() {
			@Override
			public void triggerEvent(ComponentEvent event, JQEvent jqEvent) {
				event.fire();
			}
		});
	}
	
	protected <T extends ComponentEvent> void bindEvent(final EventType eventType, final ObservableChainedValueBindingBuilder<T> eventBinding, final EventTriggerer<T> triggerer) {
		// if we already know that this event has handlers in the UI code
		ComponentEvent currentEventValue = eventBinding.getValue();
		if (currentEventValue != null && currentEventValue.isHasListeners()) {
			doBindEventInner(eventType, eventBinding, triggerer);
		} else {
			// if not yet, we add a listener por si acaso later one will be attached
			eventBinding.get(ComponentEvent_._hasListeners).addValueChangeListener(new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(Boolean newValue) {
					if (Objects.firstNonNull(newValue, false)) {
						doBindEventInner(eventType, eventBinding, triggerer);
					}
				}
			});
		}
	}
	
	protected void bindFocus(final BaseComponentModel model) {
		Bindings.obs(model).get(BaseComponentModel_._focus).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == true) {
					widget.focus();
				}
			}
		});
	}
	
	protected <T extends ComponentEvent> void doBindEventInner(EventType eventType, final ObservableValueBinding<T> eventBinding, final EventTriggerer<T> triggerer) {
		widget.bind(eventType, new EventHandler() {
			@Override
			public void eventComplete(JQEvent jqEvent, JQuery currentJQuery) {
				triggerer.triggerEvent(eventBinding.getValue(), jqEvent);
			}
		});
	}
	
	private interface EventTriggerer<E extends ComponentEvent> {
		public void triggerEvent(E event, JQEvent jqEvent);
	}
	
	
}
