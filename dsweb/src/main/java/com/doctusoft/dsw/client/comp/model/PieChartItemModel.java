package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

@Getter
@AllArgsConstructor
public class PieChartItemModel  implements ModelObject, Serializable {
	
	@ObservableProperty
	private Integer value;
	
	@ObservableProperty
	private String name;
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return PieChartEntryModel_._observableProperties;
	}
	
	
}
