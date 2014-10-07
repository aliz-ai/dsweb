package com.doctusoft.dsw.client.comp.mvp;

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

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference0;
import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference1;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.bean.binding.ParametricEventHandler;
import com.doctusoft.dsw.client.exc.ExceptionReporter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.google.gwt.user.client.ui.Widget;

public class ContainerWithPresenter<Presenter> implements ViewOf<Presenter>, HasComponentModel, Serializable {
	
	@ObservableProperty @Getter
	private Presenter presenter;
	
	protected BaseContainer container = new BaseContainer();
	
	private ObservableChainedValueBindingBuilder<Presenter> presenterBindingBuilder = null;
	
	@Override
	public Widget asWidget() {
		throw new UnsupportedOperationException("DSW views are not GWT widgets");
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getComponentModel();
	}
	
	
	@Override
	public void viewPresented() {
		
	}
	
	protected ObservableChainedValueBindingBuilder<Presenter> bindOnPresenter() {
//		if (presenterBindingBuilder == null) {
//			presenterBindingBuilder = Bindings.obs(this).get((com.doctusoft.bean.ObservableProperty) ContainerWithPresenter_._presenter);
//		}
//		return presenterBindingBuilder;
		return Bindings.obs(this).get((com.doctusoft.bean.ObservableProperty) ContainerWithPresenter_._presenter);
	}
	
	public EmptyEventHandler presenterMethod(final ClassMethodReference0<? super Presenter, Void> presenterMethod) {
		return new EmptyEventHandler() {
			@Override
			public void handle() {
				try {
					presenterMethod.apply(presenter);
				} catch (RuntimeException e) {
					if (presenter instanceof ExceptionReporter) {
						((ExceptionReporter) presenter).reportException(e);
					} else {
						throw e;
					}
				}
			}
		};
	}
	
	public <T> ParametricEventHandler<T> presenterMethod(final ClassMethodReference1<? super Presenter, Void, T> presenterMethod) {
		return new ParametricEventHandler<T>() {
			@Override
			public void handle(T parameter) {
				try {
					presenterMethod.apply(presenter, parameter);
				} catch (RuntimeException e) {
					if (presenter instanceof ExceptionReporter) {
						((ExceptionReporter) presenter).reportException(e);
					} else {
						throw e;
					}
				}
			}
		};
	}
	
	public <T> EmptyEventHandler presenterMethod(final ClassMethodReference1<? super Presenter, Void, T> presenterMethod, final T param) {
		return new EmptyEventHandler() {
			@Override
			public void handle() {
				try {
					presenterMethod.apply(presenter, param);
				} catch (RuntimeException e) {
					if (presenter instanceof ExceptionReporter) {
						((ExceptionReporter) presenter).reportException(e);
					} else {
						throw e;
					}
				}
			}
		};
	}
}
