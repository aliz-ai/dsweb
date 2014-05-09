package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

/**
 * This class is internal to {@link Select}. You are probably looking for {@link SelectItem} 
 */
@Getter
public class SelectItemModel implements ModelObject {

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return SelectItemModel_._observableProperties;
	}

	@ObservableProperty
	private String id;
	
	@ObservableProperty
	private String caption;
	
}
