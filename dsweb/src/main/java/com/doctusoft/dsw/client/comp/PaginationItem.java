package com.doctusoft.dsw.client.comp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.doctusoft.ObservableProperty;

@Data
public class PaginationItem {
	
	@Getter @Setter
	private int seq; 
	
	@ObservableProperty
	private Boolean disabled;
	
	@ObservableProperty
	private Boolean active;
	
	private String link;

}
