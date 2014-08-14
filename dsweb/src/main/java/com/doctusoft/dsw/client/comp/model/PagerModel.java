package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class PagerModel extends ContainerModel implements ModelObject {

	@ObservableProperty
	private Integer activePage = 1;

	@ObservableProperty
	private Integer numberOfPages = 1;

}
