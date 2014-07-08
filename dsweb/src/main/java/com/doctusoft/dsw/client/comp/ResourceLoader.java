package com.doctusoft.dsw.client.comp;

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
