package com.doctusoft.dsw.client.gwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.doctusoft.Property;

@AllArgsConstructor
@Getter
@Setter
public class TestDataTableRendererDTO implements Serializable {

	@Property
	private String value1;

	@Property
	private String value2;

}
