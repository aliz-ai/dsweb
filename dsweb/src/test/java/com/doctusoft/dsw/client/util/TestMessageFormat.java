package com.doctusoft.dsw.client.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMessageFormat {
	
	@Test
	public void testNoParams() {
		assertEquals("abc", MessageFormat.format("abc"));
	}

	@Test
	public void testNoParamsWithArgs() {
		assertEquals("abc", MessageFormat.format("abc", "a", "b"));
	}

	@Test
	public void testMoreParamsWithoutArgs() {
		assertEquals("abc", MessageFormat.format("abc{0}{1}"));
	}

	@Test
	public void testMoreParamsWithArgs() {
		assertEquals("abcde", MessageFormat.format("abc{0}{1}", "d", "e"));
	}

	@Test
	public void testMoreParamsWithSomeArgs() {
		assertEquals("abcd", MessageFormat.format("abc{0}{1}", "d"));
	}
}
