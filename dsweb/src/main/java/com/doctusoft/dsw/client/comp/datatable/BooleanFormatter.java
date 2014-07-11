package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.bean.binding.Converter;

public class BooleanFormatter implements Converter<Boolean, String> {
	
	private String trueLabel;
	private String falseLabel;

	public BooleanFormatter(String trueLabel, String falseLabel) {
		this.trueLabel = trueLabel;
		this.falseLabel = falseLabel;
	}
	
	@Override
	public String convertSource(Boolean source) {
		if (source == null)
			return "";
		return source?trueLabel:falseLabel;
	}
	
	@Override
	public Boolean convertTarget(String target) {
		throw new UnsupportedOperationException();
	}

}
