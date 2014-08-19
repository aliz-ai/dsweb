package com.doctusoft.dsw.client;

import java.util.LinkedList;
import java.util.Queue;

import junit.framework.Assert;

import com.doctusoft.bean.ValueChangeListener;

/**
 * TODO see {@link EmptyEventHandlerMock} 
 */
public class ValueChangeListenerMock<T> implements ValueChangeListener<T> {
	
	private Queue<T> valueQueue = new LinkedList<T>(); 

	@Override
	public void valueChanged(T newValue) {
		valueQueue.add(newValue);
	}
	
	public void assertValueChanged(T value) {
		Assert.assertTrue(!valueQueue.isEmpty());
		Assert.assertEquals(value, valueQueue.poll());
	}

	public void assertNoValueChanged() {
		Assert.assertTrue(valueQueue.isEmpty());
	}
}
