package com.doctusoft.dsw.client.comp.model;

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

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class DatePickerModel extends BaseComponentModel implements ModelObject {

	@ObservableProperty
	private Date value = null;

	/**
	 * This is the format used by bootstrap-datepicker, see http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
	 */
	@ObservableProperty
	private String format = "yyyy.MM.dd";

	@ObservableProperty
	private String placeHolder = "";

}
