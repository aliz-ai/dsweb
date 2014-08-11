package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class DataTableColumnModel implements ModelObject, Serializable {
	
	@ObservableProperty
	private String title;

}
