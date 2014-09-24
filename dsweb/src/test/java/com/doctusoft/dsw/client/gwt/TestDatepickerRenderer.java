
package com.doctusoft.dsw.client.gwt;

import java.text.ParseException;

import org.junit.Test;

import com.google.gwt.editor.client.Editor.Ignore;

public class TestDatepickerRenderer extends AbstractDswebTest {
	
	/*it prints 
	 * SEVERE: runtimeError: message=[innerText is read-only for tag tbody] sourceName=[http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/jquery-1.10.2.js] line=[6626] lineSource=[null] lineOffset=[0]
xxInternalError: innerText is read-only for tag tbody (http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/jquery-1.10.2.js#6626),@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/jquery-1.10.2.js:6626
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/jquery-1.10.2.js:6258
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/jquery-1.10.2.js:6068
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/bootstrap-datepicker.js:699
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/bootstrap-datepicker.js:151
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/bootstrap-datepicker.js:1397
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/jquery-1.10.2.js:678
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/jquery-1.10.2.js:287
@http://172.22.4.179:54642/com.doctusoft.dsw.DswTest.JUnit/bootstrap-datepicker.js:1379
@injected script:5
@eval code#1(eval):3
@injected script:111
@injected script:7
@eval code#1(eval):3
@injected script:20

	 * 
	 */
	@Test @Ignore
	public void testFormat() throws ParseException {
		System.out.println(test());
		/*
		Datepicker datePicker = new Datepicker().withId( "datepicker" );
		registerApp( datePicker );
		JQuery jqDatepicker = JQuery.select( "#datepicker" );
		jqDatepicker.val( "2014.02.22" );
		jqDatepicker.change();
		Date modelDate = datePicker.getModel().getValue();
		assertEquals( 2014, modelDate.getYear() );
		assertEquals( 1, modelDate.getMonth() );
		assertEquals( 22, modelDate.getDay() );
		*/
	}
	
	public static native String test() /*-{
		try {
			$wnd.$("<input type='text' id='xx'/>").appendTo("body");
			$wnd.$("#xx").datepicker();
		} catch (e) {
			return "xx" + e + "," + e.stack;
		}
	}-*/;
}
