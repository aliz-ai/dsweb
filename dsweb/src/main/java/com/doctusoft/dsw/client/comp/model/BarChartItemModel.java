package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

@Getter
@AllArgsConstructor
public class BarChartItemModel implements ModelObject, Serializable{
	
	@com.doctusoft.ObservableProperty
	private ObservableList<Integer> values;
	
	@com.doctusoft.ObservableProperty
	private String name;
	
	public BarChartItemModel(List<Integer> values, String name){
		this(new ObservableList<Integer>( values ), name);
	}
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return BarChartModel_._observableProperties;
	}
}
