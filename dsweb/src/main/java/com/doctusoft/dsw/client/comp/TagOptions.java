package com.doctusoft.dsw.client.comp;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class TagOptions {
	public static List<TagOption> fromStrings(String ... strings) {
		return fromStrings(Arrays.asList(strings));
	}
	
	public static List<TagOption> fromStrings(Iterable<String> string) {
		return Lists.newArrayList(Iterables.transform(string, new Function<String, TagOption>() {

			@Override
			public TagOption apply(String input) {
				TagOption item = new TagOption();
				item.setName(input);
				return item;
			}
			
		}));
	}
	
	public static <E extends Enum<E>> List<TagOption> fromEnum(E [] values) {
		return Lists.newArrayList(Lists.transform(Arrays.asList(values), new Function<E, TagOption>() {
			@Override
			public TagOption apply(E input) {
				TagOption item = new TagOption();
				item.setName(input.name());
				item.setStyleClass(input.toString());
				return item;
			}
		}));
	}

}
