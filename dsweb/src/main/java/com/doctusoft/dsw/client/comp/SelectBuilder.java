package com.doctusoft.dsw.client.comp;

import java.util.List;
import java.util.Map;

import lombok.Getter;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gwt.thirdparty.guava.common.base.Objects;

/**
 * TODO: support binding and {@link ObservableList} of {@link SelectItem}s 
 */
public class SelectBuilder<T> implements IsComponent {
	
	private Select select = new Select();
	
	@com.doctusoft.ObservableProperty @Getter
	// TODO, putting the correct 'T' type here causes ds-bean-apt to generate code with compile errors
	private Object value;
	
	private Map<T, SelectItem<T>> itemsByValue = Maps.newHashMap();
	private Map<T, SelectItemModel> modelsByValue = Maps.newHashMap();
	private List<SelectItem<T>> items = Lists.newArrayList();
	
	public SelectBuilder() {
		// if the value changes, look for the correct selectedIndex value
		SelectBuilder_._value.addChangeListener(this, new ValueChangeListener<Object>() {
			@Override
			public void valueChanged(Object newValue) {
				int candidate = getValueIndex((T) newValue);
				if (candidate != select.getSelectedIndex()) {
					select.setSelectedIndex(candidate);
				}
			}
		});
		// if the selectedIndex changes, set the correct value
		Select_._selectedIndex.addChangeListener(select, new ValueChangeListener<Integer>() {
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
	
	
	@Override
	public BaseComponent<?> asComponent() {
		return select;
	}

	protected int getValueIndex(T value) {
		if (value == null)
			return -1;
		SelectItemModel model = modelsByValue.get(value);
		if (model == null)
			return -1;
		return select.getSelectItemsModel().indexOf(model);
	}
	
	public SelectBuilder<T> bind(final ValueBinding<T> valueBinding) {
		Bindings.bind(valueBinding, (ObservableValueBinding) Bindings.obs(this).get(SelectBuilder_._value));
		return this;
	}

	public void setSelectItems(List<SelectItem<T>> selectItems) {
		select.getSelectItemsModel().clear();
		for (SelectItem<T> item : selectItems) {
			SelectItemModel itemModel = new SelectItemModel();
			itemModel.setId(item.getId());
			itemModel.setCaption(item.getCaption());
			select.getSelectItemsModel().add(itemModel);
			itemsByValue.put(item.getValue(), item);
			modelsByValue.put(item.getValue(), itemModel);
			items.add(item);
		}
	}
	
	public SelectBuilder<T> change(ValueChangeListener<T> changeListener) {
		SelectBuilder_._value.addChangeListener(this, (ValueChangeListener) changeListener);
		return this;
	}
}
