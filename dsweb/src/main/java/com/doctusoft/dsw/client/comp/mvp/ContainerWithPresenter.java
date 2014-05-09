package com.doctusoft.dsw.client.comp.mvp;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference0;
import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference1;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.gwt.light.mvp.ViewOf;
import com.google.gwt.user.client.ui.Widget;

public class ContainerWithPresenter<Presenter> implements ViewOf<Presenter>, HasComponentModel {
	
	@ObservableProperty @Getter
	private Presenter presenter;
	
	protected Container container = new Container();
	
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
		return Bindings.obs(this).get((com.doctusoft.bean.ObservableProperty) ContainerWithPresenter_._presenter);
	}
	
	protected EmptyEventHandler presenterMethod(final ClassMethodReference0<Presenter, Void> presenterMethod) {
		return new EmptyEventHandler() {
			@Override
			public void handle() {
				presenterMethod.apply(presenter);
			}
		};
	}
	protected <T> EmptyEventHandler presenterMethod(final ClassMethodReference1<Presenter, Void, T> presenterMethod, final T param) {
		return new EmptyEventHandler() {
			@Override
			public void handle() {
				presenterMethod.apply(presenter, param);
			}
		};
	}
}
