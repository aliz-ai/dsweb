package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;

@Getter
public class Button extends BaseComponent<Button> implements ModelObject {
	
	@com.doctusoft.ObservableProperty
	private String caption;

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return Button_._observableProperties;
	}
	
	/**
	 * temporal hack until the events get properly supported on models
	 */
	@com.doctusoft.ObservableProperty
	private boolean clicked;
	
	public Button() {
	}
	
	public Button(String caption) {
		setCaption(caption);
	}
	
	public Button bindCaption(final ValueBinding<String> captionBinding) {
		Bindings.bind(captionBinding, Bindings.obs(this).get(Button_._caption));
		return this;
	}
	
	public Button click(final EmptyEventHandler handler) {
		Button_._clicked.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == true) {
					handler.handle();
				}
			}
		});
		return this;
	}
	
}
