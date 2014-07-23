package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.event.ParametricEvent;

public class RowClickedEvent extends ComponentEvent implements ParametricEvent<Integer> {
	
	@com.doctusoft.ObservableProperty
	private Integer rowNumber;
	
	public void fire(int rowNumber) {
		setRowNumber(rowNumber);
		fire();
	}
	
	@Override
	public Integer getEventParameter() {
		return rowNumber;
	}
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return RowClickedEvent_._observableProperties;
	}

}
