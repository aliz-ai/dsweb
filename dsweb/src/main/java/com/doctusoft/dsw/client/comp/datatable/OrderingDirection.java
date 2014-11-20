package com.doctusoft.dsw.client.comp.datatable;

public enum OrderingDirection {
	Ascending,
	Descending;
	
	public OrderingDirection invert() {
		return (this == Ascending)?Descending:Ascending;
	}
}