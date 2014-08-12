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

import lombok.Getter;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.doctusoft.dsw.client.comp.model.event.KeyEvent;
import com.doctusoft.dsw.client.comp.model.event.KeyPressedEvent;
import com.doctusoft.dsw.client.comp.model.event.ParametricEvent;
import com.doctusoft.dsw.client.comp.model.event.ParametricEventHandler;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

@Getter
public abstract class BaseComponent<Actual, Model extends BaseComponentModel> implements HasComponentModel, Serializable {
	
	protected Model model;
	
	public BaseComponent(Model model) {
		this.model = model;
	}
	
	public Actual click(final EmptyEventHandler handler) {
		bindEvent(BaseComponentModel_._clicked, handler);
		return (Actual) this;
	}
	
	public Actual keypress(final ParametricEventHandler<KeyEvent> handler) {
		model.setKeyPressed(new KeyPressedEvent());
		bindEvent(BaseComponentModel_._keyPressed, handler, new Supplier<KeyPressedEvent>() {
			@Override
			public KeyPressedEvent get() {
				return new KeyPressedEvent();
			}
		});
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
			model.setFocus(new ComponentEvent());
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
	
	@Override
	public Model getComponentModel() {
		return model;
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
