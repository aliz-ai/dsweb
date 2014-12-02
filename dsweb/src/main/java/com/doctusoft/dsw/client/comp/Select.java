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


import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectModel;

/**
 * TODO: support binding and {@link ObservableList} of {@link SelectItem}s 
 */
public class Select<T> extends AbstractSelect<Select<T>, SelectModel, T> {
	
	public Select() {
		super(new SelectModel());
	}

	@Override
	protected void reapplyValue() {
		// the value might have been set earlier. Now that we have the possible select items, we re-fire the listeners so that the proper value is set
		if (model.getSelectedIndex() == -1) {
			setValue(getValue());
			if (getValue() == null && model.getSelectedIndex() == -1 && !items.isEmpty()) {
				// if the value is null and there's still no default option
				setValue(items.get(0).getValue());
			}
		}
	}

	
}
