package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;

public class SelectModel extends BaseComponentModel implements ModelObject {

	@ObservableProperty
	private int selectedIndex;

	@ObservableProperty
	private ObservableList<SelectItemModel> selectItemsModel = new ObservableList<SelectItemModel>();

}
