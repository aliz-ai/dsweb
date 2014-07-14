package com.doctusoft.dsw.client.comp.datatable;

import java.util.Date;

import com.doctusoft.bean.binding.Converter;
import com.doctusoft.dsw.client.util.DateTimeFormat;

public class DateFormatter implements Converter<Date, String> {
	
	private DateTimeFormat simpleDateFormat;

	public DateFormatter(String pattern) {
		simpleDateFormat = DateTimeFormat.getFormat(pattern);
	}
	
	@Override
	public String convertSource(Date source) {
		if (source == null) {
			return "";
		}
		return simpleDateFormat.format(source);
	}
	
	public Date convertTarget(String target) {
		throw new UnsupportedOperationException();
	}

}
