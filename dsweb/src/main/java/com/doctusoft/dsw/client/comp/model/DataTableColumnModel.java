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

import java.io.Serializable;

import javax.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.datatable.OrderingDirection;

public class DataTableColumnModel implements ModelObject, Serializable {
	
	@ObservableProperty
	private String title;
	
	@Getter
	@Setter
	private BaseComponentModel headerComponent;
	
	@ObservableProperty
	private boolean orderable;
	
	@ObservableProperty @Nullable
	private OrderingDirection orderingDirection;
	
	@ObservableProperty
	private ComponentEvent click;
	
	@ObservableProperty
	private Boolean visible = true;
}
