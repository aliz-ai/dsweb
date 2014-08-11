package com.doctusoft.dsw.client.comp.model;

import java.util.Date;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class DatepickerModel extends BaseComponentModel implements ModelObject {
	
	@ObservableProperty
	private Date value = null;
	
	
	/**
	 * need to use 'MM' in pattern instead of 'mm', beacause lowercase means: minutes
	 * @see http://www.gwtproject.org/javadoc/latest/com/google/gwt/i18n/client/DateTimeFormat.html
	 */
	@ObservableProperty
	private String format = "yyyy.MM.dd";

}
