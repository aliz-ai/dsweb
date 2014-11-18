package com.doctusoft.dsw.client.comp.datatable;

public abstract class AbstractColumn<Actual extends AbstractColumn<Actual,Item>, Item> implements Column<Item> {
	
	public Actual orderable() {
		getHeader().setOrderable(true);
		return (Actual) this;
	}

}
