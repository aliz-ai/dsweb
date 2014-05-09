package com.doctusoft.dsw.client.comp;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class SelectItems {

	public static List<SelectItem<String>> fromStrings(String ... strings) {
		return fromStrings(Arrays.asList(strings));
	}
	
	public static List<SelectItem<String>> fromStrings(Iterable<String> strings) {
		return Lists.newArrayList(Iterables.transform(strings, new Function<String, SelectItem<String>>() {
			@Override
			public SelectItem<String> apply(String input) {
				SelectItem<String> item = new SelectItem<String>();
				item.setCaption(input);
				item.setValue(input);
				return item;
			}
		}));
	}
}