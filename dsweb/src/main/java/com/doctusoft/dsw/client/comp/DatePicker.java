package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.util.Date;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.DatePickerModel;
import com.doctusoft.dsw.client.comp.model.DatePickerModel_;

public class DatePicker extends BaseComponent<DatePicker, DatePickerModel> {

	public DatePicker() {
		super(new DatePickerModel());
	}

	/**
	 * Uses the format of bootstrap-datepicker, see http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
	 */
	public DatePicker withFormat(final String format) {
		model.setFormat(format);
		return this;
	}

	public DatePicker bindFormat(final ValueBinding<String> format) {
		Bindings.bind(format, Bindings.obs(model).get(DatePickerModel_._format));
		return this;
	}

	public DatePicker bind(final ValueBinding<Date> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(DatePickerModel_._value));
		return this;
	}

	public DatePicker withPlaceHolder(final String placeHolder) {
		model.setPlaceHolder(placeHolder);
		return this;
	}

	public DatePicker bindPlaceHolder(final ValueBinding<String> placeholderBinding) {
		Bindings.bind(placeholderBinding, Bindings.obs(model).get(DatePickerModel_._placeHolder));
		return this;
	}

}
