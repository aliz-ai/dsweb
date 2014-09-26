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

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.Converter;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.ButtonModel_;
import com.doctusoft.dsw.client.gwt.BootstrapIcon;

@Getter
public class Button extends BaseComponent<Button, ButtonModel> {
	
	public Button() {
		super(new ButtonModel());
	}
	
	public Button(String caption) {
		this();
		model.setCaption(caption);
	}
	
	public Button withCaption(String caption) {
		model.setCaption(caption);
		return this;
	}
	
	public Button withIcon(BootstrapIcon icon) {
		model.setIconClassName((icon == null)?null:icon.getClassName());
		return this;
	}
	
	public Button withIconClassName(String iconClassName) {
		model.setIconClassName(iconClassName);
		return this;
	}

	public Button bindCaption(final ValueBinding<String> captionBinding) {
		Bindings.bind(captionBinding, Bindings.obs(model).get(ButtonModel_._caption));
		return this;
	}
	
	public Button bindIcon(final ValueBinding<BootstrapIcon> iconBinding) {
		Bindings.bind(iconBinding, Bindings.obs(model).get(ButtonModel_._iconClassName).convert(new Converter<String, BootstrapIcon>() {
			@Override
			public String convertTarget(BootstrapIcon target) {
				if (target == null)
					return null;
				return target.getClassName();
			}
			@Override
			public BootstrapIcon convertSource(String source) {
				// not needed
				return null;
			}
		}));
		return this;
	}
	
	public Button bindIconClassName(final ValueBinding<String> iconClassNameBinding) {
		Bindings.bind(iconClassNameBinding, Bindings.obs(model).get(ButtonModel_._iconClassName));
		return this;
	}
	
}
