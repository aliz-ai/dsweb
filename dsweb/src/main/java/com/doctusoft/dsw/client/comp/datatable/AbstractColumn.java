package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel_;

public abstract class AbstractColumn<Actual extends AbstractColumn<Actual,Item>, Item> implements Column<Item> {
	
	public Actual orderable() {
		getHeader().setOrderable(true);
		return (Actual) this;
	}
	
	public Actual withVisible(boolean visible) {
		getHeader().setVisible(visible);
		return (Actual) this;
	}
	
	public Actual bindVisible(ValueBinding<Boolean> visibleBinding) {
		Bindings.bind(visibleBinding, Bindings.obs(getHeader()).get(DataTableColumnModel_._visible));
		return (Actual) this;
	}

}
