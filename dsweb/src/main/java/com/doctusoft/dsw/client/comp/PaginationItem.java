package com.doctusoft.dsw.client.comp;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.ObservableProperty;

public class PaginationItem {

	@Getter @Setter
	private int seq; 
	
	@ObservableProperty
	private Boolean disabled;
	
	@ObservableProperty
	private Boolean active;
	
	@Getter @Setter
	private String link = "#";

}
