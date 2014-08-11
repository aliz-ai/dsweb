package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItem;

/**
 * This class is internal to {@link Select}. You are probably looking for {@link SelectItem} 
 */
@Getter
public class SelectItemModel implements ModelObject, Serializable {

	@ObservableProperty
	private String id;
	
	@ObservableProperty
	private String caption;
	
}
