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


import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.doctusoft.dsw.client.util.DeferredFactory;

/**
 * TODO: support binding and {@link ObservableList} of {@link SelectItem}s 
 */
public class Select<T> extends AbstractSelect<Select<T>, SelectModel, T> {
	
	private boolean nullOptionChanged = false;
	
	public Select() {
		super(new SelectModel());
		SelectModel_._nullOptionCaption.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				nullOptionChanged = true;
				deferredRunnable = DeferredFactory.defer(deferredRunnable, deferredChangeListeners);
			}
		});
	}

	@Override
	protected void applyValueChange() {
		SelectItemModel candidate = modelsByValue.get(getValue());
		if (candidate == null && !model.getSelectItemsModel().isEmpty() && model.getNullOptionCaption() == null) {
			candidate = model.getSelectItemsModel().get(0);
		}
		if (candidate != model.getSelectedItem()) {
			model.setSelectedItem(candidate);
		}
	}

	@Override
	protected void deferredRunnableExtensions() {
		if (nullOptionChanged) {
			nullOptionChanged = false;
			applyValueChange();
		}
	}

	/**
	 * If the null option caption is not null, a first option will be rendered that is associated with the null value.
	 * In this case, null value should not be among a the SelectItems (that is allowed otherwise in case you need it that way). 
	 */
	public Select<T> withNullOptionCaption(String nullOptionCaption) {
		model.setNullOptionCaption(nullOptionCaption);
		return this;
	}
	
	/**
	 * @see AbstractSelect#withNullOptionCaption(String)
	 */
	public Select<T> bindNullOptionCaption(ValueBinding<String> nullOptionCaptionBinding) {
		Bindings.bind(nullOptionCaptionBinding, Bindings.obs(model).get(SelectModel_._nullOptionCaption));
		return this;
	}
	

}
