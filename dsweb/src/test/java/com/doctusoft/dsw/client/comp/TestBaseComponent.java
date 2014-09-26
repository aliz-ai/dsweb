package com.doctusoft.dsw.client.comp;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.doctusoft.dsw.client.KeyEventHandlerMock;

public class TestBaseComponent {

	@Test
	public void testKeyEventGlobal() {
		Label label = new Label();
		KeyEventHandlerMock keyEventHandlerMock = new KeyEventHandlerMock();
		label.keypress(keyEventHandlerMock);
		assertNotNull(label.getModel().getKeyPressed());
		label.getModel().getKeyPressed().fire(21);
		Assert.assertEquals(21, keyEventHandlerMock.getReceivedKeyCode());
	}

	@Test
	public void testKeyEventSpecificSingle() {
		Label label = new Label();
		KeyEventHandlerMock keyEventHandlerMock = new KeyEventHandlerMock();
		label.keypress(keyEventHandlerMock, 21);
		label.getModel().getKeyPressed().fire(22);
		Assert.assertEquals(0, keyEventHandlerMock.getReceivedKeyCode());
		label.getModel().getKeyPressed().fire(21);
		Assert.assertEquals(21, keyEventHandlerMock.getReceivedKeyCode());
		Assert.assertArrayEquals(new Integer [] {21}, label.getModel().getRestrictToKeyCodes().toArray(new Integer [] {}));
	}

	@Test
	public void testKeyEventSpecificMultiple() {
		Label label = new Label();
		KeyEventHandlerMock keyEventHandlerMock = new KeyEventHandlerMock();
		label.keypress(keyEventHandlerMock, 21, 22);
		label.getModel().getKeyPressed().fire(23);
		Assert.assertEquals(0, keyEventHandlerMock.getReceivedKeyCode());
		label.getModel().getKeyPressed().fire(22);
		Assert.assertEquals(22, keyEventHandlerMock.getReceivedKeyCode());
		label.getModel().getKeyPressed().fire(21);
		Assert.assertEquals(21, keyEventHandlerMock.getReceivedKeyCode());
		Assert.assertArrayEquals(new Integer [] {21, 22}, label.getModel().getRestrictToKeyCodes().toArray(new Integer [] {}));
	}

	@Test
	public void testKeyEventSpecificConsequent() {
		Label label = new Label();
		KeyEventHandlerMock keyEventHandlerMock = new KeyEventHandlerMock();
		label.keypress(keyEventHandlerMock, 21);
		label.keypress(keyEventHandlerMock, 22);
		label.getModel().getKeyPressed().fire(23);
		Assert.assertEquals(0, keyEventHandlerMock.getReceivedKeyCode());
		label.getModel().getKeyPressed().fire(22);
		Assert.assertEquals(22, keyEventHandlerMock.getReceivedKeyCode());
		label.getModel().getKeyPressed().fire(21);
		Assert.assertEquals(21, keyEventHandlerMock.getReceivedKeyCode());
	}
	
	@Test
	public void testKeyEventSpecificDifferent() {
		Label label = new Label();
		KeyEventHandlerMock keyEventHandlerMock21 = new KeyEventHandlerMock();
		KeyEventHandlerMock keyEventHandlerMock22 = new KeyEventHandlerMock();
		label.keypress(keyEventHandlerMock21, 21);
		label.keypress(keyEventHandlerMock22, 22);
		label.getModel().getKeyPressed().fire(22);
		Assert.assertEquals(22, keyEventHandlerMock22.getReceivedKeyCode());
		Assert.assertEquals(0, keyEventHandlerMock21.getReceivedKeyCode());
		label.getModel().getKeyPressed().fire(21);
		Assert.assertEquals(22, keyEventHandlerMock22.getReceivedKeyCode());
		Assert.assertEquals(21, keyEventHandlerMock21.getReceivedKeyCode());
	}
	
	@Test
	public void testKeyEventGlobalAndSpecific() {
		Label label = new Label();
		KeyEventHandlerMock globalMock = new KeyEventHandlerMock();
		KeyEventHandlerMock specificMock = new KeyEventHandlerMock();
		label.keypress(globalMock);
		label.keypress(specificMock, 21);
		label.getModel().getKeyPressed().fire(22);
		Assert.assertEquals(22, globalMock.getReceivedKeyCode());
		Assert.assertEquals(0, specificMock.getReceivedKeyCode());
		label.getModel().getKeyPressed().fire(21);
		Assert.assertEquals(21, globalMock.getReceivedKeyCode());
		Assert.assertEquals(21, specificMock.getReceivedKeyCode());
	}

}
