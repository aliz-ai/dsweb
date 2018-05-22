package com.doctusoft.dsw.client;

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

/**
 * Renderers should create their widgets in the constructor, and return the prepared result in the {@link Renderer#getWidget()} method.
 */
public interface Renderer<ActualBaseComponent> {
	
	/**
	 * @return the already prepared widget
	 */
	public ActualBaseComponent getWidget();
	

	/**
	 * Notifies the renderer when the component is removed from the DOM 
	 */
	public void detach();
	
	/**
	 * When a widget is re-attached to the DOM
	 */
	public void reattach();

	/**
	 * When an element associated to a renderer is finally removed from the DOM, and all model listeners should also be removed
	 */
	public void destroy();
}
