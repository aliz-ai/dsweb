package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.binding.observable.ObservableList;

@Getter
public class Select extends BaseComponent<Select> {
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return Select_._observableProperties;
	}
	
	@com.doctusoft.ObservableProperty
	private int selectedIndex;
	
	@com.doctusoft.ObservableProperty
	private ObservableList<SelectItemModel> selectItemsModel = new ObservableList<SelectItemModel>();
	
}
