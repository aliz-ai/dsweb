package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.client.comp.TagOptions;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class SandboxActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;
	
	public SandboxActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@ObservableProperty
	private ObservableList<SelectItem<String>> locationItems = new ObservableList<SelectItem<String>>();
	
	@ObservableProperty
	private ObservableList<TagOption> tagOptions = new ObservableList<TagOption>();
	
	@ObservableProperty
	private ObservableList<TagOption> tagOptionSuggestions = new ObservableList<TagOption>();
	
	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();
	
	@ObservableProperty
	private ObservableList<String> defaultTags = new ObservableList<String>();
	
	@ObservableProperty
	private String test = "";
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<SandboxActivity> view = clientFactory.getSandboxView();
		defaultTags.clear();
		defaultTags.add("Valami");
		defaultTags.add("ezs");
		defaultTags.add("liosa");
		locationItems.addAll(SelectItems.fromStrings("asd","blup","blip"));
		tagOptionSuggestions.addAll(TagOptions.fromStrings("Ez","Az","mi","sas"));
		System.out.println("test: " + tagOptionSuggestions.get(2).getName());
		view.setPresenter(this);
		panel.setWidget(view);
		view.viewPresented();
	}
	
	@MethodRef
	public void checkBindings() {
		defaultTags.clear();
		defaultTags.addAll(tags);
		System.out.println("test: " + test);
		System.out.println("tags: " + tags);
	}
	
	
}
