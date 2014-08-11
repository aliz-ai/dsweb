package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PieChartItemModel  implements ModelObject, Serializable {
	
	@ObservableProperty
	private Integer value;
	
	@ObservableProperty
	private String name;
	
}
