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


import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.AbstractGwtRendererFactory;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Log
public abstract class AbstractRendererFactory<ActualBaseComponent> implements RendererFactory<ActualBaseComponent> {

	/**
	 * Note that this basically prevents multiple distinct dsweb applications to run at the same page
	 */
	protected static Map<BaseComponentModel, Renderer<?>> renderers = Maps.newIdentityHashMap();

	/**
	 * We might use some GC algorithm that sometimes clears up disposable components from the DOM.
	 * This requires DSWeb renderers to specify whether they are disposable or not, and a dispose method to perform any necessary operation (destroying custom jquery components for example)
	 */
	protected static Set<BaseComponentModel> disposableComponents = Sets.newIdentityHashSet();
	protected static Queue<DisposedModel> disposingQueue = new LinkedList<DisposedModel>();
	
	@Override
	public Renderer<ActualBaseComponent> getRenderer(BaseComponentModel baseWidget) {
		Renderer<ActualBaseComponent> renderer = (Renderer<ActualBaseComponent>) renderers.get(baseWidget);
		if (renderer == null) {
			renderer = resolveRenderer(baseWidget);
			renderers.put(baseWidget, renderer);
		} else {
			renderer.reattach();
			disposableComponents.remove(baseWidget);
		}
		if (renderer == null)
			throw new RuntimeException("No renderer for widget: " + baseWidget);
		return renderer;
	}
	
	public void dispose(BaseComponentModel baseComponentModel) {
		Renderer<ActualBaseComponent> renderer = (Renderer<ActualBaseComponent>) renderers.get(baseComponentModel);
		if (renderer != null) {
			renderer.detach();
		}
		disposingQueue.add(new DisposedModel(baseComponentModel, new Date().getTime()));
		disposableComponents.add(baseComponentModel);
		disposeExpiredRenderers();
	}
	
	private void disposeExpiredRenderers() {
		long now = new Date().getTime();
		int count = 0;
		while (!disposingQueue.isEmpty() && disposingQueue.peek().getDisposed() < now - 1000) {
			DisposedModel disposedModel = disposingQueue.poll();
			BaseComponentModel model = disposedModel.getModel();
			if (!disposableComponents.contains(model))
				continue;
			disposableComponents.remove(model);
			renderers.remove(model);
			count ++;
		}
		if (count > 0) {
			AbstractGwtRendererFactory.log("removed " + count + " renderers, remaining: " + renderers.size());
		}
	}

	@AllArgsConstructor
	@Getter
	private static class DisposedModel {
		private BaseComponentModel model;
		private long disposed;
	}
}
