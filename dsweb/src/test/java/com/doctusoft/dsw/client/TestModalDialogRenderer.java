
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.xedge.jquery.client.JQuery;

public class TestModalDialogRenderer extends AbstractDswebTest {
	
	@Test
	public void testHeader() {
		ModalDialog modalDialog = new ModalDialog()
			.withHeader( "header" )
			.addContent( new Label( "label" ) );
		registerApp( modalDialog );
		JQuery jqHeader = JQuery.select( "div.modal-header > h3" );
		assertEquals( "header", jqHeader.text() );
		modalDialog.getModel().setHeader( "header2" );
		assertEquals( "header2", jqHeader.text() );
	}
	
	@Test
	public void testDialogVisible() {
		ModalDialog modalDialog = new ModalDialog()
			.withId( "modal" );
		registerApp( modalDialog );
		modalDialog.show();
		JQuery jqModal = JQuery.select( "#modal" );
		assertEquals( null, jqModal.attr( "aria-hidden" ) );
		modalDialog.hide();
		assertEquals( "true", jqModal.attr( "aria-hidden" ) );
	}
	
	@Test
	public void testCloseDialog() {
		ModalDialog modalDialog = new ModalDialog()
			.withId( "modal" );
		modalDialog.show();
		registerApp( modalDialog );
		JQuery closeButton = JQuery.select( "button" );
		closeButton.click();
		//		assertFalse( modalDialog.getModel().getDialogVisible() ); TODO dialogClosed nem hivodik at nativbol
	}
	
	@Test
	public void testFooterContainer() {
		ModalDialog modalDialog = new ModalDialog()
			.withId( "modal" ).addFooter( new Label( "footer" ) );
		modalDialog.show();
		registerApp( modalDialog );
		JQuery jqFooter = JQuery.select( "#modal div.modal-footer" );
		assertEquals( "footer", jqFooter.text() );
	}
	
	@Test
	public void testContentContainer() {
		ModalDialog modalDialog = new ModalDialog().withId( "modal" ).addContent( new Label( "hello" ) );
		modalDialog.show();
		registerApp( modalDialog );
		JQuery jqBody = JQuery.select( "#modal div.modal-body" );
		dumpRoot();
		assertEquals( "hello", jqBody.text() );
	}
	
}
