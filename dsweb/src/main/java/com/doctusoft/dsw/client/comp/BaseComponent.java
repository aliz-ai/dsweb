package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.google.common.base.Preconditions;

@Getter
public abstract class BaseComponent<Actual, Model extends BaseComponentModel> implements HasComponentModel {
	
	protected Model model;
	
	public BaseComponent(Model model) {
		this.model = model;
	}
	
	public Actual click(final EmptyEventHandler handler) {
		bindEvent(BaseComponentModel_._clicked, handler);
		return (Actual) this;
	}
	
	public Actual bindVisible(final ValueBinding<Boolean> visibleBinding) {
		Bindings.bind(visibleBinding, Bindings.obs(model).get(BaseComponentModel_._visible));
		return (Actual) this;
	}
	
	public void addStyleClass(String styleClass) {
		Preconditions.checkArgument(styleClass.split(" ").length == 1, "Only one style class allowed to be added at a time");
		if (!model.getStyleClasses().contains(styleClass)) {
			model.getStyleClasses().add(styleClass);
		}
	}
	
	public Actual withStyleClass(String styleClass) {
		addStyleClass(styleClass);
		return (Actual) this;
	}
	
	public void removeStyleClass(String styleClass) {
		model.getStyleClasses().remove(styleClass);
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
	
	public Actual bindStyle(final ObservableValueBinding<String> styleBinding) {
		styleBinding.addValueChangeListener(new ValueChangeListener<String>() {
			
			@Override
			public void valueChanged(String newValue) {
				setStyle(styleBinding.getValue());
				
			}
		});
		return (Actual) this;
	}
	
	public void appendTo(IsContainer container) {
		container.add(this);
	}
	
	@Override
	public Model getComponentModel() {
		return model;
	}
	
	protected void bindEvent(ObservableProperty<? super Model, ComponentEvent> eventProperty, final EmptyEventHandler handler) {
		Bindings.obs(model).get(eventProperty).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == true) {
					handler.handle();
				}
			}
		});
	}
}
