package com.doctusoft.dsw.client.comp;

import java.util.List;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * TODO: support binding and {@link ObservableList} of {@link SelectItem}s 
 */
public class Select<T> extends AbstractSelect<Select, SelectModel, T> {
	
	public Select() {
		super(new SelectModel());
	}
	
}
