package com.doctusoft.dsw.client.comp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.doctusoft.ObservableProperty;

@NoArgsConstructor
@AllArgsConstructor
public class ResourceLoaderModel extends BaseComponentModel {
	
	@ObservableProperty
	private String resourcePah;
	
	@ObservableProperty
	private ResourceType resourceType;
	
	public enum ResourceType {
		Script,
		Stylesheet;
	}
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return ResourceLoaderModel_._observableProperties;
	}

}
