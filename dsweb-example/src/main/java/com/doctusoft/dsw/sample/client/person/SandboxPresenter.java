package com.doctusoft.dsw.sample.client.person;

/*
 * #%L
 * dsweb-example
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.mvp.AbstractPresenter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class SandboxPresenter extends AbstractPresenter<SandboxPresenter> {

	@Getter
	private ViewOf<SandboxPresenter> view;

	@ObservableProperty
	private BigDecimal inputNumberValue;
	
	@ObservableProperty
	private Boolean disabled = false;
	
	@ObservableProperty
	private String button1 = "Set Disabled all inputs";
	
	@ObservableProperty
	private String button2 = "test Button";

	public SandboxPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getSandboxView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<SandboxPresenter> implements Serializable {
		public Place() {
			super("sandbox", SandboxPresenter.class );
		}
	}
	
	@MethodRef
	public void changeDisabled() {
		setDisabled(!getDisabled());
	}

}
