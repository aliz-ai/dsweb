package com.doctusoft.dsw.client.util;

/**
 * A simple portable (GWT-JVM) implementation for message formatting (replacing {0} stuff)
 */
public class MessageFormat {
	
	public static String format(final String format, final Object... args) {
	    StringBuilder sb = new StringBuilder();
	    int cur = 0;
	    int len = format.length();
	    while (cur < len) {
	        int fi = format.indexOf('{', cur);
	        if (fi != -1) {
	            sb.append(format.substring(cur, fi));
	            int si = format.indexOf('}', fi);
	            if (si != -1) {
	                String nStr = format.substring(fi + 1, si);
	                int i = Integer.parseInt(nStr);
	                if (i < args.length) {
                		sb.append(args[i]);
	                }
	                cur = si + 1;
	            } else {
	                sb.append(format.substring(fi));
	                break;
	            }
	        } else {
	            sb.append(format.substring(cur, len));
	            break;
	        }
	    }
	    return sb.toString();
	}

}
