
package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.client.comp.TagOptions;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
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
	private ObservableList<TagOption> tagOptions = new ObservableList<TagOption>();
	
	@ObservableProperty
	private ObservableList<TagOption> tagOptionSuggestions = new ObservableList<TagOption>();
	
	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();
	
	@ObservableProperty
	private ObservableList<String> tagSuggestions = new ObservableList<String>();
	
	@ObservableProperty
	private String test = "2014.12.03";
	
	@ObservableProperty
	private boolean visibility;
	
	@ObservableProperty
	private ComponentEvent focus = new ComponentEvent();
	
	public SandboxActivity( ClientFactory clientFactory ) {
		this.clientFactory = clientFactory;
	}
	
	@MethodRef
	public void checkBindings() {
		tagSuggestions.clear();
		tagSuggestions.addAll( tags );
		System.out.println( "test: " + test );
		System.out.println( "tags: " + tags );
		System.out.println( "visibility: " + visibility );
	}
	
	@Override
	public void start( AcceptsOneWidget panel, EventBus eventBus ) {
		ViewOf<SandboxActivity> view = clientFactory.getSandboxView();
		tagSuggestions.clear();
		tagSuggestions.add( "SimaStringes" );
		tagSuggestions.add( "21312Stringes" );
		tagSuggestions.add( "sdasdStringes" );
		locationItems.addAll( SelectItems.fromStrings( "asd", "blup", "blip" ) );
		tagOptionSuggestions.addAll( TagOptions.fromStrings( "Ez", "Az", "mi", "sas" ) );
		TagOption tagOp = new TagOption();
		tagOp.setName( "Ez már más" );
		tagOp.setStyleClass( "label label-warning" );
		TagOption tagOp2 = new TagOption();
		tagOp2.setName( "Ez már megint más" );
		tagOp2.setStyleClass( "label label-success" );
		tagOptionSuggestions.add( tagOp2 );
		tagOptionSuggestions.add( tagOp );
		tags.add( "asdas" );
		view.setPresenter( this );
		panel.setWidget( view );
		view.viewPresented();
	}
	
}
