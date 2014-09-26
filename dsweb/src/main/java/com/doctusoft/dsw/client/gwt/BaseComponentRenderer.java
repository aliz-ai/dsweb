package com.doctusoft.dsw.client.gwt;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
import com.doctusoft.dsw.client.util.Deferred;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.JQuery.EventType;
import com.xedge.jquery.client.handlers.EventHandler;

public class BaseComponentRenderer implements Renderer<JQuery> {
	
	protected static <T, K> void addChangeListenerAndApply(ObservableProperty<K, T> property, BaseComponentModel model, ValueChangeListener<T> listener) {
		addChangeListener(property, model, listener);
		listener.valueChanged(property.getValue((K) model));
	}
	
	protected static <T, K> void addChangeListener(ObservableProperty<K, T> property, BaseComponentModel model, ValueChangeListener<T> listener) {
		property.addChangeListener((K) model, listener);
	}
	
	@Getter
	protected JQuery widget;
	
	protected boolean visible = true;
	
	public BaseComponentRenderer(final JQuery widget, final BaseComponentModel model) {
		this.widget = widget;
		
		if (model.getId() != null) {
			widget.attr("id", model.getId());
		}
		Deferred.defer(new Runnable() {
			@Override
			public void run() {
				// applying visibility has to wait for the component to be actually inserted into the DOM
				applyVisible(model.getVisible());
			}
		}); 
		addChangeListener(BaseComponentModel_._visible, model, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				applyVisible(newValue);
			}
		});
		new ListBindingListener<String>(Bindings.obs(model).get(BaseComponentModel_._styleClasses)) {
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
		addChangeListenerAndApply(BaseComponentModel_._style, model, new ValueChangeListener<String>() {
			
			@Override
			public void valueChanged(String newValue) {
				applyStyle(newValue);
			}
		});
		addChangeListenerAndApply(BaseComponentModel_._tabIndex, model, new ValueChangeListener<Integer>() {

			@Override
			public void valueChanged(Integer newValue) {
				applyTabIndex(newValue);
			}
		});
		
		bindEvent(EventType.click, Bindings.obs(model).get(BaseComponentModel_._clicked));
		bindEvent(EventType.keypress, Bindings.obs(model).get(BaseComponentModel_._keyPressed), new EventTriggerer<KeyPressedEvent>() {
			@Override
			public void triggerEvent(KeyPressedEvent event, JQEvent jqEvent) {
				// only send event for keycodes that are registered on the server side
				if (model.getRestrictToKeyCodes() == null || model.getRestrictToKeyCodes().contains(jqEvent.getKeyCode())) {
					event.fire(jqEvent.getKeyCode());
				}
			}
		});
		
		bindFocus(model);
	}
	
	protected void applyVisible(Boolean visible) {
		Boolean newVisible = Objects.firstNonNull(visible, true);
		if (newVisible && !this.visible) {
			widget.show();
		}
		if (!newVisible && this.visible) {
			widget.hide();
		}
		this.visible = newVisible;
	}
	
	protected void applyStyle(String style) {
		widget.attr("style", Strings.isNullOrEmpty(style)?null:style);
	}
	
	protected void applyTabIndex(Integer tabIndex) {
		widget.attr("tabindex", tabIndex == null ? null : String.valueOf(tabIndex));
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
				if (Objects.firstNonNull(newValue, false)) {
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
