package com.doctusoft.dsw.client.comp;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.google.common.collect.Lists;

@Getter
public abstract class BaseComponent<Actual> implements ModelObject, IsComponent {
	
	@com.doctusoft.ObservableProperty
	private boolean visible = true;
	
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> styleClasses = new ObservableList<String>();
	
	@com.doctusoft.ObservableProperty
	private boolean hover;
	
	protected ObservableProperty<?, ?>[] extend(ObservableProperty<?, ?> [] superProperties, ObservableProperty<?, ?> ... additionals) {
		if (superProperties == null)
			return additionals;
		// nb: this causes a compilation error with GWT
//		return ObjectArrays.concat((ObservableProperty []) superProperties, (ObservableProperty []) additionals, ObservableProperty.class);
		List<ObservableProperty> result = Lists.newArrayList();
		result.addAll(Arrays.asList(superProperties));
		result.addAll(Arrays.asList(additionals));
		return result.toArray(new ObservableProperty [] {});
	}
	
	
	public Actual bindVisible(final ValueBinding<Boolean> visibleBinding) {
		Bindings.bind(visibleBinding, Bindings.obs(this).get(BaseComponent_._visible));
		return (Actual) this;
	}
	
	public void addStyleClass(String styleClass) {
		if (!styleClasses.contains(styleClass)) {
			styleClasses.add(styleClass);
		}
	}
	
	public void removeStyleClass(String styleClass) {
		styleClasses.remove(styleClass);
	}
	
	public Actual onHover(final EmptyEventHandler handler) {
		BaseComponent_._hover.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == true) {
					handler.handle();
				}
			}
		});
		return (Actual) this;
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
	
	public void appendTo(IsContainer container) {
		container.add(this);
	}
	
	@Override
	public BaseComponent<?> asComponent() {
		return this;
	}
}