package com.doctusoft.dsw.client.comp;

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
	
	private Map<T, SelectItem<T>> itemsByValue = Maps.newHashMap();
	private Map<T, SelectItemModel> modelsByValue = Maps.newHashMap();
	private List<SelectItem<T>> items = Lists.newArrayList();

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
		if (value == null)
			return -1;
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
	}
	
	public void setSelectItems(List<SelectItem<T>> selectItems) {
		model.getSelectItemsModel().clear();
		itemsByValue.clear();
		modelsByValue.clear();
		int index = 0;
		for (SelectItem<T> item : selectItems) {
			registerSelectItem(index ++, item);
		}
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
				itemsByValue.remove(element.getValue());
				modelsByValue.remove(element.getValue());
				model.getSelectItemsModel().remove(index);
				items.remove(index);
			}
		};
		return (Actual) this;
	}

}
