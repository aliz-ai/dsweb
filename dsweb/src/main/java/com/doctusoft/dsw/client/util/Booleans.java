package com.doctusoft.dsw.client.util;

/**
 * Utility methods that handles null Boolean values as false 
 */
@Deprecated
public class Booleans {
	
	public static boolean isTrue(Boolean b) {
		return Boolean.TRUE.equals(b);
	}
	
	public static String toString(Boolean b) {
		return Boolean.toString(isTrue(b));
	}

}
