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


import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public abstract class AbstractContainer<Actual, Model extends AbstractContainerModel<? extends BaseComponentModel>>
		extends BaseComponent<Actual, Model> implements IsContainer {
	
	public AbstractContainer(Model model) {
		super(model);
	}
	
	/**
	 * Removes all child components 
	 */
	public void clear() {
		getModel().getChildren().clear();
	}
	
	public Actual insert(int index, HasComponentModel component) {
		addToSpecifiedIndex(model.getChildren(), index, component.getComponentModel());
		return (Actual) this;
	}
	
	public Actual prepend(HasComponentModel component) {
		addToSpecifiedIndex(model.getChildren(), 0, component.getComponentModel());
		return (Actual) this;
	}
	
	@Override
	public Actual add(HasComponentModel component) {
		addWithWildCardCapture(model.getChildren(), component.getComponentModel());
		return (Actual) this;
	}
	
	private <T> void addToSpecifiedIndex(ObservableList<T> list, int index, BaseComponentModel model) {
		list.add(index, (T) model);
	}
	
	@Override
	public Actual remove(HasComponentModel component) {
		removeWithWildCardCapture(model.getChildren(),component.getComponentModel());
		return (Actual) this;
	}
	
	private <T> void removeWithWildCardCapture(ObservableList<T> list, BaseComponentModel model) {
		list.remove((T) model);
	}
	
	private <T> void addWithWildCardCapture(ObservableList<T> list, BaseComponentModel model) {
		list.add((T) model);
	}
	
}
