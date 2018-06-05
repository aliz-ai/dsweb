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


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.doctusoft.bean.ListenerRegistration;
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
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.JQuery.EventType;
import com.xedge.jquery.client.handlers.EventHandler;

public class BaseComponentRenderer implements Renderer<JQuery> {

	public <T, K> ListenerRegistration addChangeListenerAndApply(final ObservableProperty<K, T> property, final K model, final ValueChangeListener<T> listener) {
		ListenerRegistration changeListener = addChangeListener(property, model, listener);
		listener.valueChanged(property.getValue(model));
		return changeListener;
	}

	public <T, K> ListenerRegistration addChangeListener(final ObservableProperty<K, T> property, final K model, final ValueChangeListener<T> listener) {
		ListenerRegistration registration = property.addChangeListener(model, listener);
		if (listenerRegistrations == null) {
			listenerRegistrations = new ArrayList<ListenerRegistration>();
		}
		listenerRegistrations.add(registration);
		return registration;
	}

	@Getter
	protected JQuery widget;

	protected boolean visible = true;
	
	protected List<ListenerRegistration> listenerRegistrations;

	public BaseComponentRenderer(final JQuery widget, final BaseComponentModel model) {
		this.widget = widget;

		if (model.getId() != null) {
			widget.attr("id", model.getId());
		}

		new ListBindingListener<String>(Bindings.obs(model).get(BaseComponentModel_._styleClasses)) {
			@Override
			public void inserted(final ObservableList<String> list, final int index,
					final String element) {
				widget.addClass(element);
			}

			@Override
			public void removed(final ObservableList<String> list, final int index,
					final String element) {
				widget.removeClass(element);
			}
		};
		addChangeListenerAndApply(BaseComponentModel_._style, model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(final String newValue) {
				applyStyle(newValue);
			}
		});

		// apply visibility after style, because style would override 'display: none' otherwise
		addChangeListenerAndApply(BaseComponentModel_._visible, model, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(final Boolean newValue) {
				applyVisible(newValue);
			}
		});
		
		addChangeListenerAndApply(BaseComponentModel_._tabIndex, model, new ValueChangeListener<Integer>() {

			@Override
			public void valueChanged(final Integer newValue) {
				applyTabIndex(newValue);
			}
		});

		bindEvent(EventType.click, Bindings.obs(model).get(BaseComponentModel_._clicked));
		bindEvent(EventType.keypress, Bindings.obs(model).get(BaseComponentModel_._keyPressed), new EventTriggerer<KeyPressedEvent>() {
			@Override
			public void triggerEvent(final KeyPressedEvent event, final JQEvent jqEvent) {
				// only send event for keycodes that are registered on the server side
				if (model.getRestrictToKeyCodes() == null || model.getRestrictToKeyCodes().contains(jqEvent.getKeyCode())) {
					event.fire(jqEvent.getKeyCode());
				}
			}
		});

		bindFocus(model);
	}

	/**
	 * TODO disabled should not really be in BaseComponentModel. We should have some base class for edit components
	 */
	protected void applyDisabled(final boolean disabled) {

	}

	protected void applyVisible(final Boolean visible) {
		Boolean newVisible = Objects.firstNonNull(visible, true);
		if (newVisible && !this.visible) {
			widget.show();
		}
		if (!newVisible && this.visible) {
			widget.hide();
		}
		this.visible = newVisible;
	}

	protected void applyStyle(final String style) {
		widget.attr("style", Strings.isNullOrEmpty(style)?null:style);
	}

	protected void applyTabIndex(final Integer tabIndex) {
		widget.attr("tabindex", tabIndex == null ? null : String.valueOf(tabIndex));
	}

	protected void bindEvent(final EventType eventType, final ObservableChainedValueBindingBuilder<ComponentEvent> eventBinding) {
		bindEvent(widget, eventType, eventBinding);
	}

	protected void bindEvent(final JQuery target, final EventType eventType, final ObservableChainedValueBindingBuilder<ComponentEvent> eventBinding) {
		bindEvent(target, eventType, eventBinding, new EventTriggerer<ComponentEvent>() {
			@Override
			public void triggerEvent(final ComponentEvent event, final JQEvent jqEvent) {
				event.fire();
			}
		});
	}

	protected <T extends ComponentEvent> void bindEvent(final EventType eventType, final ObservableChainedValueBindingBuilder<T> eventBinding, final EventTriggerer<T> triggerer) {
		bindEvent(widget, eventType, eventBinding, triggerer);
	}
	protected <T extends ComponentEvent> void bindEvent(final JQuery target, final EventType eventType, final ObservableChainedValueBindingBuilder<T> eventBinding, final EventTriggerer<T> triggerer) {
		// if we already know that this event has handlers in the UI code
		ComponentEvent currentEventValue = eventBinding.getValue();
		if (currentEventValue != null && currentEventValue.isHasListeners()) {
			doBindEventInner(target, eventType, eventBinding, triggerer);
		} else {
			// if not yet, we add a listener por si acaso later one will be attached
			eventBinding.get(ComponentEvent_._hasListeners).addValueChangeListener(new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(final Boolean newValue) {
					if (Objects.firstNonNull(newValue, false)) {
						doBindEventInner(target, eventType, eventBinding, triggerer);
					}
				}
			});
		}
	}

	protected void bindFocus(final BaseComponentModel model) {
		Bindings.obs(model).get(BaseComponentModel_._focus).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(final Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					widget.focus();
				}
			}
		});
	}

	protected <T extends ComponentEvent> void doBindEventInner(final JQuery target, final EventType eventType, final ObservableValueBinding<T> eventBinding, final EventTriggerer<T> triggerer) {
		target.bind(eventType, new EventHandler() {
			@Override
			public void eventComplete(final JQEvent jqEvent, final JQuery currentJQuery) {
				triggerer.triggerEvent(eventBinding.getValue(), jqEvent);
			}
		});
	}

	public interface EventTriggerer<E extends ComponentEvent> {
		public void triggerEvent(final E event, final JQEvent jqEvent);
	}

	@Override
	public void detach() {
		// empty default implementation
	}

	@Override
	public void reattach() {
		// empty default implementation
	}
	
	@Override
	public void destroy() {
		for (ListenerRegistration registration : listenerRegistrations) {
			registration.removeHandler();
		}
		listenerRegistrations.clear();
	}
}
