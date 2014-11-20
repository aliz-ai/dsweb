package com.doctusoft.dsw.client.comp;

import lombok.Data;

/**
 * Should be treated as immutable VO 
 */
@Data
public class TagOption<T> {
	
	private String name;
	
	private String styleClass;
	
	private T value;

}
