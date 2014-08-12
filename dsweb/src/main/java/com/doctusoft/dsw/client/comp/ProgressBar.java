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
		StripedActive("progress-striped active"),
		Info("progress-info"),
		Success("progress-success"),
		Warning("progress-warning"),
		Danger("progress-danger");
		
		@Getter
		private String name;
		
		ProgressBarType(String name) {
			this.name = name;
		}
	}

}
