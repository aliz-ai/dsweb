package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.SelectItemModel;

public class SelectModel extends BaseComponentModel {

	@com.doctusoft.ObservableProperty
	private int selectedIndex;

	@com.doctusoft.ObservableProperty
	private ObservableList<SelectItemModel> selectItemsModel = new ObservableList<SelectItemModel>();

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return SelectModel_._observableProperties;
	}

}
