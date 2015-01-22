
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.Checkbox;
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class TestCheckboxRenderer extends AbstractDswebTest {
	
	@ObservableProperty
	private boolean value;
	
	@Test
	public void testLabel() {
		Checkbox checkbox = new Checkbox( "initial" ).withId( "checkbox" );
		registerApp( checkbox );
		assertEquals( "initial", JQuery.select( "#checkbox" ).text() );
		checkbox.getModel().setLabel( "changed" );
		assertEquals( "changed", JQuery.select( "#checkbox" ).text() );
	}
	
	@Test
	public void testChecked() {
		Checkbox checkbox = new Checkbox().withId( "checkbox" );
		CheckboxModel model = checkbox.getModel();
		model.setChecked( true );
		registerApp( checkbox );
		assertTrue( JQuery.select( "#checkbox > input" ).is( ":checked" ) );
		model.setChecked( false );
		new Timer() {
			@Override
			public void run() {
				assertFalse( JQuery.select( "#checkbox > input" ).is( ":checked" ) );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(500);
	}
	
	@Test
	public void testCheckboxClick() {
		Checkbox checkbox = new Checkbox().withId( "checkbox" );
		CheckboxModel model = checkbox.getModel();
		model.setChecked( true );
		registerApp( checkbox );
		JQuery.select( "#checkbox > input" ).click();
		assertFalse( model.getChecked() );
		JQuery.select( "#checkbox > input" ).click();
		assertTrue( model.getChecked() );
	}
	
	/**
     * Only test if disabling works. Other aspects of {@link EnabledAttributeRenderer} are tested in {@link TestInputTextRenderer}
     */
    @Test
    public void testDisabledFirst() {
        final Checkbox checkbox = new Checkbox().withId("checkbox").withEnabled(false);
        registerApp(checkbox);
        assertTrue(JQuery.select("#checkbox > input").is(":disabled"));
    }
    
    /**
     * A test case for https://github.com/Doctusoft/dsweb/issues/72
     */
    @Test
    public void testCheckboxUncheckedByApplicationCode() {
		Checkbox checkbox = new Checkbox().withId( "checkbox" );
		setValue(false);
		checkbox.bindChecked(Bindings.obs(this).get(TestCheckboxRenderer_._value));
		registerApp( checkbox );
		TestCheckboxRenderer_._value.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == Boolean.TRUE) {
					setValue(false);
				}
			}
		});
		JQuery.select( "#checkbox > input" ).click();
		new Timer() {
			@Override
			public void run() {
				assertFalse( JQuery.select( "#checkbox > input" ).is( ":checked" ) );
				finishTest();
			}
		}.schedule(50);
		delayTestFinish(500);
    }
}
