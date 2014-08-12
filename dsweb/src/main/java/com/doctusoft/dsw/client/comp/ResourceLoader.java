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


import com.doctusoft.dsw.client.comp.model.ResourceLoaderModel;

/**
 * This component can be used to dynamically load a single stylesheet or script.
 * This is not a real UI component, but still extends {@link BaseComponent} to conform other parts of the framework. 
 */
public class ResourceLoader extends BaseComponent<ResourceLoader, ResourceLoaderModel> {
	
	public ResourceLoader(ResourceLoaderModel.ResourceType resourceType, String path) {
		super(new ResourceLoaderModel(path, resourceType));
	}

}
