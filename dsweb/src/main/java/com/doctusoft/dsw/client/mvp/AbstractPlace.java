package com.doctusoft.dsw.client.mvp;

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

import lombok.Getter;

import com.google.common.base.Objects;

/**
 * This ties Presenter and AbstractPlace, as known for the GWT MVP framework. In GWT's solution,
 * Activities and Places are decoupled. A history tokenizer maps uri fragments to Places, and then an
 * activity mapper maps Places to activities. That gives more freedom, but that's not currently necessary
 * for our basic needs.
 * 
 * The design of this AbstractPlace class has another flaw. The parametric constructor is used by the actual Presenters to define their places.
 * The default constructor is used by the {@link NavigationHandler} to create these places, and it then invokes
 * {@link AbstractPlace#parseFragment(String)} to handle any possible parameters.
 * GWT MVP uses a Tokenizer for the latter.
 */
public abstract class AbstractPlace<Presenter extends com.doctusoft.dsw.client.mvp.Presenter<Presenter>> implements Serializable {
	
	protected String fragment;
	@Getter
	private Class<Presenter> presenterClass;
	@Getter
	private String fragmentRoot;
	
	public void parseFragment(String fragment) {
		// do nothing by default
	}
	
	public String generateFragment() {
		return fragmentRoot;
	}

	public AbstractPlace(String fragmentRoot, Class<Presenter> presenterClass) {
		this.fragmentRoot = fragmentRoot;
		this.presenterClass = presenterClass;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!obj.getClass().equals(getClass()))
			return false;
		return Objects.equal(generateFragment(), ((AbstractPlace)obj).generateFragment());
	}
}
