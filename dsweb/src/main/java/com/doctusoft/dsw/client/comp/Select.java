package com.doctusoft.dsw.client.comp;

import java.util.List;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gwt.thirdparty.guava.common.base.Objects;

/**
 * TODO: support binding and {@link ObservableList} of {@link SelectItem}s 
 */
public class Select<T> extends BaseComponent<Select<T>, SelectModel> {
	
	@com.doctusoft.ObservableProperty
	private T value;
	
	private Map<T, SelectItem<T>> itemsByValue = Maps.newHashMap();
	private Map<T, SelectItemModel> modelsByValue = Maps.newHashMap();
	private List<SelectItem<T>> items = Lists.newArrayList();
	
	public Select() {
		super(new SelectModel());
		// if the value changes, look for the correct selectedIndex value
		Select_._value.addChangeListener(this, new ValueChangeListener<Object>() {
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
	
	
	protected int getValueIndex(T value) {
		if (value == null)
			return -1;
		SelectItemModel itemModel = modelsByValue.get(value);
		if (itemModel == null)
			return -1;
		return model.getSelectItemsModel().indexOf(itemModel);
	}
	
	public Select<T> bind(final ValueBinding<T> valueBinding) {
		Bindings.bind(valueBinding, (ObservableValueBinding) Bindings.obs(this).get(Select_._value));
		return this;
	}

	public void setSelectItems(List<SelectItem<T>> selectItems) {
		model.getSelectItemsModel().clear();
		for (SelectItem<T> item : selectItems) {
			SelectItemModel itemModel = new SelectItemModel();
			itemModel.setId(item.getId());
			itemModel.setCaption(item.getCaption());
			model.getSelectItemsModel().add(itemModel);
			itemsByValue.put(item.getValue(), item);
			modelsByValue.put(item.getValue(), itemModel);
			items.add(item);
		}
	}
	
	public Select<T> change(ValueChangeListener<T> changeListener) {
		Select_._value.addChangeListener(this, (ValueChangeListener) changeListener);
		return this;
	}
}
