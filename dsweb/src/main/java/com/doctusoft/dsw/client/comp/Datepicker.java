package com.doctusoft.dsw.client.comp;

import java.util.Date;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.DatepickerModel;
import com.doctusoft.dsw.client.comp.model.DatepickerModel_;

public class Datepicker extends BaseComponent<Datepicker, DatepickerModel> {

	public Datepicker() {
		super(new DatepickerModel());
	}
	
	public Datepicker withFormat(String format) {
		model.setFormat(format);
		return this;
	}
	
	public Datepicker bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(DatepickerModel_._value));
		return this;
	}
	
	public Datepicker bindDate(final ValueBinding<Date> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(DatepickerModel_._dateValue));
		return this;
	}
}
