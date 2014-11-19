package com.doctusoft.dsw.client.comp.datatable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * A simple fast solution to have the ordering information bound on the presenter.
 * Havig the column index is not too handy at most cases, but providing more useful information
 * is not at all trivial, and needs some more time to think on. (Gabor, 2014.11.19).
 * 
 * This descriptor is an immutable value object, because you want to listen to its changes as a single data unit (not individually to index and direction changes) 
 */
@Getter
@AllArgsConstructor
@ToString
public class SingleDataTableOrdering implements Serializable {

	private int orderColumnIndex;
	
	private OrderingDirection direction;

}
