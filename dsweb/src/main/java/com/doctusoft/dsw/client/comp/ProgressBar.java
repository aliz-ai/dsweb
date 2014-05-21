package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;


public class ProgressBar extends BaseContainer {
	
	private BaseContainer bar;
	
	@ObservableProperty
	private Integer progress = 60;;
	
	public ProgressBar() {
		addStyleClass("progress");
		bar = new BaseContainer();
		bar.addStyleClass("bar");
		bar.setStyle("width: " + progress + "%");
		add(bar);
		
		Bindings.obs(this).get(ProgressBar_._progress).addValueChangeListener(new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				bar.setStyle("width: " + newValue + "%");
			}
		});
		
	}
	
	public ProgressBar bind(ValueBinding<Integer> progressBinding) {
		Bindings.bind(progressBinding, Bindings.obs(this).get(ProgressBar_._progress));
		return this;
	}
	
	public ProgressBar setType(ProgressBarType type) {
		for (String styleClass : type.getName().split(" ")) {
			addStyleClass(styleClass);
		}
		return this;
	}
	
	public static enum ProgressBarType {
		
		Striped("progress-striped"),
		StripedActive("progress-striped active");
		
		@Getter
		private String name;
		
		ProgressBarType(String name) {
			this.name = name;
		}
	}

}
