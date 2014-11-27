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


import java.util.Map;
import java.util.Set;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public abstract class AbstractRendererFactory<ActualBaseComponent> implements RendererFactory<ActualBaseComponent> {

	/**
	 * Note that this basically prevents multiple distinct dsweb applications to run at the same page
	 */
//	protected static Map<BaseComponentModel, Renderer<?>> renderers = Maps.newIdentityHashMap();
	protected static Map<BaseComponentModel, Renderer<?>> renderers = Maps.newHashMap();

	/**
	 * We might use some GC algorithm that sometimes clears up disposable components from the DOM.
	 * This requires DSWeb renderers to specify whether they are disposable or not, and a dispose method to perform any necessary operation (destroying custom jquery components for example)
	 */
//	protected static Set<BaseComponentModel> disposableComponents = Sets.newIdentityHashSet();
	protected static Set<BaseComponentModel> disposableComponents = Sets.newHashSet();
	
	@Override
	public Renderer<ActualBaseComponent> getRenderer(BaseComponentModel baseWidget) {
		Renderer<ActualBaseComponent> renderer = (Renderer<ActualBaseComponent>) renderers.get(baseWidget);
		if (renderer == null) {
			renderer = resolveRenderer(baseWidget);
			renderers.put(baseWidget, renderer);
			disposableComponents.remove(baseWidget);
		}
		if (renderer == null)
			throw new RuntimeException("No renderer for widget: " + baseWidget);
		return renderer;
	}
	
	public void dispose(BaseComponentModel baseComponentModel) {
		disposableComponents.add(baseComponentModel);
	}

}
