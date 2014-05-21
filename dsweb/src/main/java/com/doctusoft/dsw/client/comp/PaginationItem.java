package com.doctusoft.dsw.client.comp;

import lombok.Data;

import com.doctusoft.ObservableProperty;

@Data
public class PaginationItem {
	
	private int seq; 
	
	@ObservableProperty
	private Boolean disabled;
	
	@ObservableProperty
	private Boolean active;
	
	private String link = "#";

}
