package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BarChartItemModel implements ModelObject, Serializable{
	
	@ObservableProperty
	private ObservableList<Integer> values;
	
	@ObservableProperty
	private String name;
	
	public BarChartItemModel(List<Integer> values, String name){
		this(new ObservableList<Integer>( values ), name);
	}
	
}
