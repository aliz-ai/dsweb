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


import java.util.List;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class AbstractSelect<Actual, Model extends SelectModel, T> extends BaseComponent<Actual, Model> {
	
	@com.doctusoft.ObservableProperty
	private T value;
	
	protected Map<T, SelectItem<T>> itemsByValue = Maps.newHashMap();
	protected Map<T, SelectItemModel> modelsByValue = Maps.newHashMap();
	protected List<SelectItem<T>> items = Lists.newArrayList();

	public AbstractSelect(final Model model) {
		super(model);
		// if the value changes, look for the correct selectedIndex value
		AbstractSelect_._value.addChangeListener(this, new ValueChangeListener<Object>() {
			@Override
			public void valueChanged(Object newValue) {
				int candidate = getValueIndex((T) newValue);
				if (candidate != model.getSelectedIndex()) {
					model.setSelectedIndex(candidate);
				}
			}
		});
		// if the selectedIndex changes, set the correct value
		SelectModel_._selectedIndex.addChangeListener(model, new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				if (newValue == null || newValue == -1) {
					if (value != null) {
						setValue(null);
					}
				} else {
					if (items.isEmpty()) {
						return;
					}
					SelectItem<T> candidate = items.get(newValue);
					if (candidate == null) {
						setValue(null);
					} else {
						T candidateValue = candidate.getValue();
						if (!Objects.equal(value, candidateValue)) {
							setValue(candidateValue);
						}
					}
				}
			}
		});
	}
	
	public Actual change(ValueChangeListener<T> changeListener) {
		AbstractSelect_._value.addChangeListener(this, (ValueChangeListener) changeListener);
		return (Actual) this;
	}
	
	public Actual bind(final ValueBinding<T> valueBinding) {
		Bindings.bind(valueBinding, (ObservableValueBinding) Bindings.obs(this).get(AbstractSelect_._value));
		return (Actual) this;
	}
	
	protected int getValueIndex(T value) {
		SelectItemModel itemModel = modelsByValue.get(value);
		if (itemModel == null) {
			return -1;
		}
		return model.getSelectItemsModel().indexOf(itemModel);
	}
	
	public Actual withSelectItems(List<SelectItem<T>> selectItems) {
		setSelectItems(selectItems);
		return (Actual) this;
	}
	
	protected void registerSelectItem(int index, SelectItem<T> item) {
		SelectItemModel itemModel = new SelectItemModel();
		itemModel.setId(item.getId());
		itemModel.setCaption(item.getCaption());
		model.getSelectItemsModel().add(index, itemModel);
		itemsByValue.put(item.getValue(), item);
		modelsByValue.put(item.getValue(), itemModel);
		items.add(index, item);
		reapplyValue();
	}
	
	public void setSelectItems(List<SelectItem<T>> selectItems) {
		model.getSelectItemsModel().clear();
		itemsByValue.clear();
		modelsByValue.clear();
		int index = 0;
		for (SelectItem<T> item : selectItems) {
			registerSelectItem(index ++, item);
		}
		reapplyValue();
	}

	protected void reapplyValue() {
		// the value might have been set earlier. Now that we have the possible select items, we re-fire the listeners so that the proper value is set
		if (model.getSelectedIndex() == -1) {
			setValue(value);
		}
	}
	
	public Actual bindSelectItems(ObservableValueBinding<? extends List<SelectItem<T>>> selectItemsBinding) {
		new ListBindingListener<SelectItem<T>>(selectItemsBinding) {
			@Override
			public void inserted(ObservableList<SelectItem<T>> list, int index, SelectItem<T> element) {
				registerSelectItem(index, element);
			}
			@Override
			public void removed(ObservableList<SelectItem<T>> list, int index, SelectItem<T> element) {
				// TODO this should not be done in case of Typeahead for example, when select options are merely suggestions
				if (Objects.equal(element.getValue(), value)) {
					setValue(null);
				}
				itemsByValue.remove(element.getValue());
				modelsByValue.remove(element.getValue());
				model.getSelectItemsModel().remove(index);
				items.remove(index);
			}
		};
		return (Actual) this;
	}

}
