package com.doctusoft.dsw.client.comp;

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


import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ParametricEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableMap;
import com.doctusoft.bean.binding.observable.ObservableMap.MapElementInsertedListener;
import com.doctusoft.bean.binding.observable.ObservableMap.MapElementRemovedListener;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.doctusoft.dsw.client.comp.model.event.KeyEvent;
import com.doctusoft.dsw.client.comp.model.event.KeyPressedEvent;
import com.doctusoft.dsw.client.comp.model.event.ParametricEvent;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;

@Getter
public abstract class BaseComponent<Actual, Model extends BaseComponentModel> implements HasComponentModel, Serializable {
	
	protected Model model;
	
	protected Map<Integer, ParametricEventHandler<KeyEvent>> specificKeyHandlers;
	protected KeyEventDispatcher specificKeyDispatcher;
	protected boolean globalKeyHandlerRegistered = false;
	
	private ObservableMap<String, String> inlineCssStyles;

	public BaseComponent(Model model) {
		this.model = model;
		inlineCssStyles = new ObservableMap<String, String>();
		inlineCssStyles.addDeleteListener(new MapElementRemovedListener<String, String>() {
			@Override
			public void removed(ObservableMap<String, String> map, String key, String element) {
				applyCssChanges();
			}
		});
		inlineCssStyles.addInsertListener(new MapElementInsertedListener<String, String>() {

			@Override
			public void inserted(ObservableMap<String, String> map, String key, String element) {
				applyCssChanges();
			}
		});
	}
	
	public Actual click(final EmptyEventHandler handler) {
		bindEvent(BaseComponentModel_._clicked, handler);
		return (Actual) this;
	}
	
	/**
	 * Listens to all keypress events on the given node. Use this wisely. If you only need to know about a few key codes, like Enter and Escape keys, use {@link BaseComponent#keypress(ParametricEventHandler, int...)}
	 */
	public Actual keypress(final ParametricEventHandler<KeyEvent> handler) {
		globalKeyHandlerRegistered = true;
		if (model.getRestrictToKeyCodes() != null) {
			model.setRestrictToKeyCodes(null);
		}
		bindKeyPressInner(handler);
		return (Actual) this;
	}
	
	private void bindKeyPressInner(final ParametricEventHandler<KeyEvent> handler) {
		bindEvent(BaseComponentModel_._keyPressed, handler, new Supplier<KeyPressedEvent>() {
			@Override
			public KeyPressedEvent get() {
				return new KeyPressedEvent();
			}
		});
	}
	
	/**
	 * Listens to specific key codes. 
	 */
	public Actual keypress(final ParametricEventHandler<KeyEvent> handler, int ... keyCodes) {
		if (specificKeyDispatcher == null) {
			// register the dispatcher and make it listen to all key events
			specificKeyDispatcher = new KeyEventDispatcher();
			bindKeyPressInner(specificKeyDispatcher);
		}
		if (specificKeyHandlers == null) {
			specificKeyHandlers = Maps.newHashMap();
		}
		for (int keyCode : keyCodes) {
			specificKeyHandlers.put(keyCode, handler);
		}
		if (!globalKeyHandlerRegistered) {
			if (model.getRestrictToKeyCodes() == null) {
				model.setRestrictToKeyCodes(new ObservableList<Integer>());
			}
			ObservableList<Integer> restrictList = model.getRestrictToKeyCodes();
			for (int keyCode : keyCodes) {
				restrictList.add(keyCode);
			}
		}
		return (Actual) this;
	}

	
	public Actual bindFocus(final ObservableChainedValueBindingBuilder<ComponentEvent> eventBinding) {
		eventBinding.get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				focus();
			}
		});
		return (Actual) this;
	}
	
	public Actual focus() {
		if (model.getFocus() == null) {
			ComponentEvent event = new ComponentEvent();
			event.setHasListeners(true);	// it always has a listener on the Renderer side
			model.setFocus(event);
		}
		model.getFocus().fire();
		return (Actual) this;
	}
	
	public Actual bindVisible(final ValueBinding<Boolean> visibleBinding) {
		Bindings.bind(visibleBinding, Bindings.obs(model).get(BaseComponentModel_._visible));
		return (Actual) this;
	}
	
	public void addStyleClass(String styleClass) {
		Preconditions.checkArgument(styleClass.split(" ").length == 1, "Only one style class allowed to be added at a time");
		if (model.getStyleClasses() == null) {
			model.setStyleClasses(new ObservableList<String>());
		}
		if (!model.getStyleClasses().contains(styleClass)) {
			model.getStyleClasses().add(styleClass);
		}
	}
	
	public Actual withStyleClass(String styleClass) {
		addStyleClass(styleClass);
		return (Actual) this;
	}
	
	public Actual withStyleClasses(String ... styleClasses) {
		for (String styleClass : styleClasses) {
			addStyleClass(styleClass);
		}
		return (Actual) this;
	}

	public void removeStyleClass(String styleClass) {
		if (model.getStyleClasses() != null) {
			model.getStyleClasses().remove(styleClass);
		}
	}
	
	public Actual bindStyleClassPresent(final String styleClass, final ObservableValueBinding<Boolean> presentBinding) {
		presentBinding.addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue) {
					addStyleClass(styleClass);
				} else {
					removeStyleClass(styleClass);
				}
			}
		});
		return (Actual) this;
	}
	
	public void setStyle(String style) {
		model.setStyle(style);
	}
	
	public Actual withStyle(String style) {
		setStyle(style);
		return (Actual) this;
	}
	
	public void setTabIndex(int tabIndex) {
		model.setTabIndex(tabIndex);
	}
	
	public Actual withTabIndex(int tabIndex) {
		setTabIndex(tabIndex);
		return (Actual) this;
	}
	
	public Actual bindStyle(final ObservableValueBinding<String> styleBinding) {
		styleBinding.addValueChangeListener(new ValueChangeListener<String>() {
			
			@Override
			public void valueChanged(String newValue) {
				setStyle(styleBinding.getValue());
				
			}
		});
		return (Actual) this;
	}
	
	public Actual appendTo(IsContainer container) {
		container.add(this);
		return (Actual) this;
	}
	
	public Actual withId(String id) {
		model.setId(id);
		return (Actual) this;
	}
	
	public Actual withVisible(boolean visible) {
		model.setVisible(visible);
		return (Actual) this;
	}
	
	@Override
	public Model getComponentModel() {
		return model;
	}
	
	public Actual css(String name, String value) {
		inlineCssStyles.put(name, value);
		return (Actual) this;
	}

	public String css(String properyName) {
		return inlineCssStyles.get(properyName);
	}

	public Actual bindCss(final String name, ObservableChainedValueBindingBuilder<String> binding) {
		inlineCssStyles.put(name, binding.getValue());
		binding.addValueChangeListener(new ValueChangeListener<String>() {

			@Override
			public void valueChanged(String newValue) {
				inlineCssStyles.put(name, newValue);
			}
		});
		return (Actual) this;
	}

	private void applyCssChanges() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> cssStyle : inlineCssStyles.entrySet()) {
			if (cssStyle.getKey() != null && cssStyle.getValue() != null) {
				builder.append(cssStyle.getKey());
				builder.append(":");
				builder.append(cssStyle.getValue());
				builder.append(";");
			}
		}
		model.setStyle(builder.toString());
	}

	protected class KeyEventDispatcher implements ParametricEventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent keyEvent) {
			if (specificKeyHandlers == null)
				return;
			ParametricEventHandler<KeyEvent> handler = specificKeyHandlers.get(keyEvent.getCode());
			if (handler != null) {
				handler.handle(keyEvent);
			}
		}
	}
	
	protected void bindEvent(ObservableProperty<? super Model, ComponentEvent> eventProperty, final EmptyEventHandler handler) {
		ComponentEvent event = eventProperty.getValue(model);
		if (event == null) {
			event = new ComponentEvent();
			eventProperty.setValue(model, event);
		}
		event.setHasListeners(true);
		Bindings.obs(model).get(eventProperty).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					handler.handle();
				}
			}
		});
	}

	protected <T, E extends ComponentEvent & ParametricEvent<T>> void bindEvent(final ObservableProperty<? super Model, E> eventProperty, final ParametricEventHandler<T> handler, Supplier<E> eventObjectSupplier) {
		E event = eventProperty.getValue(model);
		if (event == null) {
			event = eventObjectSupplier.get();
			eventProperty.setValue(model, event);
		}
		event.setHasListeners(true);
		Bindings.obs(model).get(eventProperty).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					handler.handle(eventProperty.getValue(model).getEventParameter());
				}
			}
		});
	}

}
