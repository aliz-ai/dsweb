package com.doctusoft.dsw.client.comp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

@NoArgsConstructor
@AllArgsConstructor
public class ResourceLoaderModel extends BaseComponentModel implements ModelObject {
	
	@ObservableProperty
	private String resourcePah;
	
	@ObservableProperty
	private ResourceType resourceType;
	
	public enum ResourceType {
		Script,
		Stylesheet;
	}
	
}
