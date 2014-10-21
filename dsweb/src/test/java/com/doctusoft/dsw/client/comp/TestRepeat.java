package com.doctusoft.dsw.client.comp;

import org.junit.Test;

import com.doctusoft.bean.binding.observable.ObservableList;

public class TestRepeat {

	@Test
	public void testObservableListSetItems() {
		ObservableList<String> sourceList = new ObservableList<String>();
		sourceList.add("hello");
		new Repeat<String>() {
			@Override
			protected BaseComponent<?, ?> renderItem(String item, int rowNum) {
				System.out.println("added " + item);
				return new Label(item);
			}
		}.setItems(sourceList);
		sourceList.add("world");
	}

}
