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


import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.CellModel;
import com.doctusoft.dsw.client.comp.model.CellModel_;

public class Cell extends AbstractContainer<Cell, CellModel> {
	
	public Cell() {
		super(new CellModel());
	}
	
	public void setSpan(int span) {
		model.setSpan(span);
	}
	
	public void setOffset(int offset) {
		model.setOffset(offset);
	}
	
	public Cell withSpan(int span) {
		setSpan(span);
		return this;
	}
	
	public Cell withOffset(int offset) {
		setOffset(offset);
		return this;
	}
    
    public Cell bindSpan(final ValueBinding<Integer> spanBinding) {
		Bindings.bind(spanBinding, Bindings.obs(model).get(CellModel_._span));
		return this;
	}
    
    public Cell bindOffset(final ValueBinding<Integer> offsetBinding) {
		Bindings.bind(offsetBinding, Bindings.obs(model).get(CellModel_._offset));
		return this;
	}
	
}
