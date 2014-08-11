package com.doctusoft.dsw.client.comp.model.event;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class KeyEvent implements ModelObject, Serializable {
	
	@ObservableProperty
	private Integer code; 
	

}
