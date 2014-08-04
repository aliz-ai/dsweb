package com.doctusoft.dsw.client.comp.model;

import java.util.Date;

import com.doctusoft.bean.ObservableProperty;

public class DatepickerModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private Date value = null;
	
	
	/**
	 * need to use 'MM' in pattern instead of 'mm', beacause lowercase means: minutes
	 * @see http://www.gwtproject.org/javadoc/latest/com/google/gwt/i18n/client/DateTimeFormat.html
	 */
	@com.doctusoft.ObservableProperty
	private String format = "yyyy.MM.dd";
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return DatepickerModel_._observableProperties;
	}

}
