package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class DatepickerModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private String value = "";
	
	@com.doctusoft.ObservableProperty
	private String format = "yyyy.mm.dd";
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return DatepickerModel_._observableProperties;
	}

}
