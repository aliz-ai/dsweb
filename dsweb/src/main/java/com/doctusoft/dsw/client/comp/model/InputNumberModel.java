package com.doctusoft.dsw.client.comp.model;

import java.math.BigDecimal;

import com.doctusoft.ObservableProperty;

/**
 * TODO support other value input properties
 */
public class InputNumberModel extends BaseComponentModel {

	@ObservableProperty
	private BigDecimal value;

	@ObservableProperty
	private BigDecimal max;

	@ObservableProperty
	private BigDecimal min;

	@ObservableProperty
	private String placeHolder = "";
}
