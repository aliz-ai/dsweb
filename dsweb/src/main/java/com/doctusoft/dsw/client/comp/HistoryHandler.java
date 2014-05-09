package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.ObservableProperty;

/**
 * Not a real UI component 
 */
public class HistoryHandler extends BaseComponent<HistoryHandler> {
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return HistoryHandler_._observableProperties;
	}
	
	@ObservableProperty @Getter
	private String historyToken;
	
}
