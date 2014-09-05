
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.xedge.jquery.client.JQuery;

public class TestChildrenRenderer extends AbstractDswebTest {
	
	@Test
	public void testComponent() {
		//link contains a ChildrenRenderer
		Link link = new Link().add( new Label( "egy" ) ).add( new Label( "ketto" ) ).withId( "link" );
		registerApp( link );
		JQuery jqLink = JQuery.select( "#link" );
		assertEquals( "<span>egy</span><span>ketto</span>", jqLink.html() );
		ObservableList<BaseComponentModel> children = link.getModel().getChildren();
		children.remove( 1 );
		assertEquals( "<span>egy</span>", jqLink.html() );
		link.add( new Label( "harom" ) );
		assertEquals( "<span>egy</span><span>harom</span>", jqLink.html() );
		dumpRoot();
	}
	
}
