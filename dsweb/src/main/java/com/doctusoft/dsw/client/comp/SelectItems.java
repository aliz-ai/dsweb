package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
	
	public static <E extends Enum<E>> List<SelectItem<E>> fromEnum(E [] values) {
		return Lists.newArrayList(Lists.transform(Arrays.asList(values), new Function<E, SelectItem<E>>() {
			@Override
			public SelectItem<E> apply(E input) {
				SelectItem<E> item = new SelectItem<E>();
				item.setCaption(input.name());
				item.setValue(input);
				return item;
			}
		}));
	}
}
