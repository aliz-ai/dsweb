package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import lombok.Getter;


@Getter
public class ChartItemClickParam implements Serializable{
	
	private final int itemIndex;
	
	private final int subIndex;
	
	public ChartItemClickParam( int itemIndex, int subIndex ) {
		super();
		this.itemIndex = itemIndex;
		this.subIndex = subIndex;
	}
	
}
