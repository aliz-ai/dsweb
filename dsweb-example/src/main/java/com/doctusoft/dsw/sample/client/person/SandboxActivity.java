
package com.doctusoft.dsw.sample.client.person;

import java.util.Date;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class SandboxActivity extends AbstractActivity {
	
	private final ClientFactory clientFactory;
	
	@ObservableProperty
	private ObservableList<SelectItem<String>> locationItems = new ObservableList<SelectItem<String>>();
	
	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private SelectionMode selectionMode = SelectionMode.Single;
	
	@ObservableProperty
	private ObservableList<PersonDto> selection = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private boolean visibility = false;
	
	@ObservableProperty
	private ComponentEvent focus = new ComponentEvent();
	
	public SandboxActivity( ClientFactory clientFactory ) {
		this.clientFactory = clientFactory;
	}
	
	@MethodRef
	public void checkBindings() {
		System.out.println( "visibility: " + visibility );
	}
	
	@Override
	public void start( AcceptsOneWidget panel, EventBus eventBus ) {
		ViewOf<SandboxActivity> view = clientFactory.getSandboxView();
		locationItems.addAll( SelectItems.fromStrings( "asd", "blup", "blip" ) );
		
		personList.add(new PersonDto(1l, "Compay Segundo", "compay@buena.cu", new Date(7, 10, 18)));
		personList.add(new PersonDto(2l, "Omara Portuondo", "omara@buena.cu", new Date(30, 9, 29)));
		personList.add(new PersonDto(3l, "Ibrahim Ferrer", "ibrahim@buena.cu", new Date(6, 7, 27)));
		view.setPresenter( this );
		panel.setWidget( view );
		view.viewPresented();
	}
	
	@MethodRef
	public void hideLabel() {
		personList.removeAll(selection);
		setVisibility(false);
	}
}
