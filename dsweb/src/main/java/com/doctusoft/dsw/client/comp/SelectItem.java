package com.doctusoft.dsw.client.comp;

import java.io.Serializable;

import lombok.Data;

@Data
public class SelectItem<T> implements Serializable {
	
	/**
	 * This id will be rendered as name attribute of the option element.
	 * You don't necessarily need this, but if you plan Selenium tests, you should better fill them :)
	 * Nb: the internals of the Select component don't rely on this value - it's index based.
	 */
	private String id;
	
	/**
	 * This will be displayed on the screen for the user
	 */
	private String caption;
	
	/**
	 * This is the value that will be bound to your data model
	 */
	private T value;

}
